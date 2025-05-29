package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class Topping {
    private String name;
    private double basePrice;

    protected abstract double getTotalPrice();

    public abstract String getRepresentation();
}
