package com.pluralsight.model.sandwich;

import java.util.Map;

public class PremiumToppings extends Topping {

    private final String name;
    private final Map<Size, Double> price;

    public PremiumToppings(String name, Map<Size, Double> price) {
        super(name);
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return price.get(Size.MEDIUM);
    }

    enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
}
