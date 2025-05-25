package com.pluralsight.model.extras;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;

@Data
public class Drink extends OrderItem {

    private String name;
    private double price;


    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
