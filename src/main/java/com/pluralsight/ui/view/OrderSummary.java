package com.pluralsight.ui.view;

import com.pluralsight.model.order.Order;

public class OrderSummary {
    public static void mock() {
        System.out.println("Order Summary:");
        System.out.println("1. Chips: $2.50");
        System.out.println("2. Soda: $1.50");
        System.out.println("3. Candy: $1.00");
        System.out.println("Total: $5.00");
        System.out.println("Thank you for your order!");
    }

    public static void mock(Order order) {

    }
}
