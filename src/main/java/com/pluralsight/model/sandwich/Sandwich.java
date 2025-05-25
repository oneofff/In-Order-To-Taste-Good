package com.pluralsight.model.sandwich;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Sandwich extends OrderItem {

    private UnitPrice unitPrice;
    private String breadType;
    private boolean toasted;
    private List<Topping> toppings;

    @Override
    public String getDescription() {
        return "Sandwich with " + unitPrice.getSize() + " size, " + breadType + " bread, " +
                (toasted ? "toasted" : "not toasted") + ", and toppings: " + toppings;
    }

    @Override
    public double getPrice() {
        return calculateBasePrice() + calculateToppingPrice();
    }

    private double calculateToppingPrice() {
        return toppings.stream()
                .mapToDouble(topping -> topping.getPrice(unitPrice.getSize()))
                .sum();
    }

    private double calculateBasePrice() {
        return unitPrice.getPrice();
    }
}
