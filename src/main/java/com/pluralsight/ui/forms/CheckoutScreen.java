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
        ScreenUtils.printOnCenterOfTheScreen("Enter gratuity amount: ");
        double gratuity = ConsoleStringReader.getPositiveDoubleWithMargin();
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
}
