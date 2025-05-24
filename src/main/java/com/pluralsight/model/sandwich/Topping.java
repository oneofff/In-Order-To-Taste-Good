package com.pluralsight.model.sandwich;

import lombok.Data;

@Data
public abstract class Topping {
    private String name;


    public Topping(String name) {
        this.name = name;
    }

    public abstract String getDescription();

    public abstract double getPrice();
}
