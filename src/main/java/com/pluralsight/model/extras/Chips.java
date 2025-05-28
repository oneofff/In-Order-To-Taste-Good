package com.pluralsight.model.extras;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Chips extends OrderItem {

    private String name;
    private double price;

    @Override
    public List<String> getRepresentation() {
        return List.of(
                "Chips: " + name,
                String.format("Price: $%.2f", price)
        );
    }

    @Override
    public List<String> getShortRepresentation() {
        return List.of(String.format("%s - $%.2f", name, price));
    }

    @Override
    public double getTotalPrice() {
        return price;
    }
}
