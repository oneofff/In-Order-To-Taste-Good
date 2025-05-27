package com.pluralsight.model.sandwich;

import lombok.Data;

@Data
public class PremiumTopping extends Topping {

    private String type;
    private double basePrice;
    private double extraPrice;
    private String size;
    private boolean isExtra;

    @Override
    public double getPrice(String size) {
        return basePrice + (isExtra ? extraPrice : 0.0);
    }
}
