package com.pluralsight.model.sandwich;

import lombok.Data;

@Data
public class Sauce {
    private String name;
    private double price;

    public String getRepresentation() {
        return String.format("%s - $%.2f", name, price);
    }

}
