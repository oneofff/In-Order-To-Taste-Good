package com.pluralsight.model.sandwich;

import lombok.Data;

import java.util.Map;

@Data
public class Topping {

    private String name;
    private Map<String, Double> price;

    public String getDescription() {
        return name;
    }

    public double getPrice(String size) {
        return price.get(size);
    }
}
