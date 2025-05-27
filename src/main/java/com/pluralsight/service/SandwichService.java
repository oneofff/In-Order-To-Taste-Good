package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;

public class SandwichService {


    public Order addCustomSandwichToOrder(Order order, CustomSandwich customSandwich) {
        if (customSandwich == null || customSandwich.getSizeName() == null || customSandwich.getBread() == null) {
            throw new IllegalArgumentException("Sandwich cannot be null and must have a size and bread selected.");
        }
        order.addItem(customSandwich);
        return order;
    }
}
