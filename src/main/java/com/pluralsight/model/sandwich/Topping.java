package com.pluralsight.model.sandwich;

import lombok.Data;

@Data
public abstract class Topping {
    private String name;

    protected abstract double getPrice(String size);
}
