package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignatureSandwich extends Sandwich {

    private List<String> ingredients;
    private double basePrice;

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public double getPrice() {
        return basePrice;
    }
}
