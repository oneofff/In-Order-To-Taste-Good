package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.service.OrderService;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckoutScreen {

    private final OrderService orderService = new OrderService();

    public void checkout(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            ScreenUtils.cls();
            ScreenUtils.printBox(List.of(
                    "Your order is empty.",
                    "Please add items to your order before checking out."
            ));
            ScreenUtils.waitTillPressEnter();
            ScreenUtils.cls();
            return;
        }
        ScreenUtils.cls();
        ScreenUtils.printOnCenterOfTheScreen("Checkout:");
        ScreenUtils.printBox(order.getOrderRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("Please enter a gratuity (e.g. 3.50 or 20%).");
        double gratuity = getGratuity(order.getPriceWithTaxes());
        order.setGratuity(gratuity);
        ScreenUtils.cls();
        ScreenUtils.printBox(order.getOrderCheckoutRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("Do you want to confirm the order? 1=Yes, 2=No");
        boolean isConfirmed = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        if (isConfirmed) {
            order.setOrderDate(LocalDateTime.now());
            orderService.saveOrder(order);
            ScreenUtils.cls();
            ScreenUtils.printBox(List.of(
                    "Thank you for your order!",
                    "Your order has been placed successfully.",
                    "Order Date: " + order.getOrderDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")),
                    "Total Price: $" + String.format("%.2f", order.getTotalPrice())
            ));
            ScreenUtils.waitTillPressEnter();
            ScreenUtils.cls();
        } else {
            ScreenUtils.printOnCenterOfTheScreen("Order cancelled. You can continue shopping.");
        }
    }

    private static double getGratuity(double priceWithTaxes) {
        while (true) {
            String raw = ConsoleStringReader.getStringWithMargin().trim();

            if (raw.isEmpty()) {
                ScreenUtils.printOnCenterOfTheScreen("Please enter a gratuity (e.g. 3.50 or 20%).");
                continue;
            }

            boolean percentInput = raw.endsWith("%");
            String numericPart = percentInput
                    ? raw.substring(0, raw.length() - 1).trim()
                    : raw;

            try {
                double value = Double.parseDouble(numericPart);
                if (value < 0) {
                    ScreenUtils.printOnCenterOfTheScreen("Gratuity cannot be negative.");
                    continue;
                }

                double gratuity = percentInput
                        ? priceWithTaxes * value / 100.0
                        : value;

                return Math.round(gratuity * 100.0) / 100.0;

            } catch (NumberFormatException ex) {
                ScreenUtils.printOnCenterOfTheScreen("Invalid number format. Try again.");
            }
        }
    }
}
