package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.service.DefaultOrderService;
import com.pluralsight.service.interfaces.OrderService;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckoutScreen {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    private final OrderService orderService = new DefaultOrderService();

    public void checkout(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            emptyOrderMessage();
            return;
        }
        printCheckoutScreen(order);
        order.setGratuity(getGratuity(order.getPriceWithTaxes()));
        ScreenUtils.cls();
        boolean isConfirmed = getOrderConfirmation(order);
        if (!isConfirmed) {
            ScreenUtils.printOnCenterOfTheScreen("Order cancelled. You can continue shopping.");
            return;
        }
        confirmOrder(order);
    }

    private static boolean getOrderConfirmation(Order order) {
        ScreenUtils.printBox(order.getOrderCheckoutRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("Do you want to confirm the order? 1=Yes, 2=No");
        boolean s = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        return s;
    }

    private static void printCheckoutScreen(Order order) {
        ScreenUtils.cls();
        ScreenUtils.printOnCenterOfTheScreen("Checkout:");
        ScreenUtils.printBox(order.getOrderRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("Please enter a gratuity (e.g. 3.50 or 20%).");
    }

    private void emptyOrderMessage() {
        ScreenUtils.cls();
        ScreenUtils.printBox(List.of(
                "Your order is empty.",
                "Please add items to your order before checking out."
        ));
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private void confirmOrder(Order order) {
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(now);
        orderService.saveOrder(order);
        ScreenUtils.cls();
        ScreenUtils.printBox(List.of(
                "Thank you for your order!",
                "Your order has been placed successfully.",
                "Order Date: " + now.format(DATE_FORMATTER),
                "Total Price: $" + String.format("%.2f", order.getTotalPrice())
        ));
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private static double getGratuity(double total) {
        while (true) {
            String input = ConsoleStringReader.getStringWithMargin().trim();
            if (input.isEmpty()) {
                ScreenUtils.printOnCenterOfTheScreen("Please enter a gratuity (e.g. 3.50 or 20%).");
                continue;
            }
            boolean isPercent = input.endsWith("%");
            String number = isPercent ? input.substring(0, input.length() - 1).trim() : input;
            try {
                double value = Double.parseDouble(number);
                if (value < 0) {
                    ScreenUtils.printOnCenterOfTheScreen("Gratuity cannot be negative.");
                    continue;
                }
                double amount = isPercent ? total * value / 100.0 : value;
                return Math.round(amount * 100.0) / 100.0;
            } catch (NumberFormatException e) {
                ScreenUtils.printOnCenterOfTheScreen("Invalid number format. Try again.");
            }
        }
    }
}
