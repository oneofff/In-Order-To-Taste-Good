package com.pluralsight.model.extras;

import com.pluralsight.model.order.OrderItem;

public class Chips extends OrderItem {

    private final String name;
    private final double price;

    public Chips(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
