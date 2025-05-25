package com.pluralsight.model.sandwich;

public class CustomSandwich extends Sandwich {

    private UnitPrice unitPrice;

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
