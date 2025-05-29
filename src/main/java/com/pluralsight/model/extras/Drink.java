package com.pluralsight.model.extras;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Drink extends OrderItem {

    private String name;
    private String size;
    private double price;


    @Override
    public List<String> getRepresentation() {
        return List.of(
                "Drink: " + getName(),
                "Size: " + getSize(),
                String.format("Price: $%.2f", getPrice())
        );
    }

    @Override
    public List<String> getShortRepresentation() {
        return List.of(
                String.format("%s  %s - $%.2f", getName(), getSize(), getPrice())
        );
    }

    @Override
    public double getTotalPrice() {
        return price;
    }
}
