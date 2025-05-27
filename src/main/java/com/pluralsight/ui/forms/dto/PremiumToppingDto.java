package com.pluralsight.ui.forms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PremiumToppingDto {
    private String category;
    private String name;
    private double basePrice;
    private double extraPrice;
    private String size;
    private boolean isExtra;

    public String getRepresentation() {
        return String.format("- %s, %s $%.2f",
                name, isExtra ? "Extra" : "Regular", getTotalPrice());
    }

    public double getTotalPrice() {
        return basePrice + (isExtra ? extraPrice : 0.0);
    }
}
