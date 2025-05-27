package com.pluralsight.model.extras;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class Drink extends OrderItem {

    private String name;
    private double price;


    @Override
    public List<String> getRepresentation() {
        return List.of();
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }
}
