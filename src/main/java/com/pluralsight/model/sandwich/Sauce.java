package com.pluralsight.model.sandwich;

import lombok.Data;

@Data
public class Sauce {
    private String name;
    private double price;

    public String getRepresentation() {
        return String.format("%s %s", name, price == 0.0 ? "" : String.format("- $%.2f", price));
    }
}
