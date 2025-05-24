package com.pluralsight.model.sandwich;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Sandwich extends OrderItem {

    private Size size;
    private BreadType breadType;
    private boolean toasted;
    private List<Topping> toppings;

    @Override
    public String getDescription() {
        return "Sandwich with " + size + " size, " + breadType + " bread, " +
                (toasted ? "toasted" : "not toasted") + ", and toppings: " + toppings;
    }

    @Override
    public double getPrice() {
        return calculateBasePrice() + calculateToppingPrice();
    }

    private double calculateToppingPrice() {
        return toppings.stream()
                .mapToDouble(Topping::getPrice)
                .sum();
    }

    private double calculateBasePrice() {
        return size.basePrice;
    }

    @RequiredArgsConstructor
    private enum Size {
        SMALL(5.50), MEDIUM(7.00), LARGE(8.50);

        private final double basePrice;

    }

    private enum BreadType {
        WHITE, WHEAT, RYE, WRAP
    }
}
