package com.pluralsight.model.sandwich;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class PremiumTopping extends Topping {
    private String category;
    private double extraPrice;
    private String size;
    private boolean isExtra;

    public String getRepresentation() {
        return String.format("- %s, %s %s",
                getName(), isExtra ? "Extra" : "Regular", getTotalPrice() == 0.0 ? "" : String.format(" -$%.2f", getTotalPrice()));
    }

    public double getTotalPrice() {
        return getBasePrice() + (isExtra ? extraPrice : 0.0);
    }
}
