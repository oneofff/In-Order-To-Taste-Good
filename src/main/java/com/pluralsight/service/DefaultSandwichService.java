package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.service.interfaces.SandwichService;

public class DefaultSandwichService implements SandwichService {
    @Override
    public void addCustomSandwichToOrder(Order order, CustomSandwich customSandwich) {
        if (customSandwich == null || customSandwich.getSize() == null || customSandwich.getBread() == null) {
            throw new IllegalArgumentException("Sandwich cannot be null and must have a size and bread selected.");
        }
        order.addItem(customSandwich);
    }
} 