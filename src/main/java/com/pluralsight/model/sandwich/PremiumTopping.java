package com.pluralsight.model.sandwich;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class PremiumTopping extends Topping {
    private String category;
    private double extraPrice;
    private String size;
    private boolean isExtra;

    public String getRepresentation() {
        return String.format("- %s, %s $%.2f",
                getName(), isExtra ? "Extra" : "Regular", getTotalPrice());
    }

    public double getTotalPrice() {
        return getBasePrice() + (isExtra ? extraPrice : 0.0);
    }
}
