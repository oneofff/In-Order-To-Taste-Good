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
    public List<String> getRepresentation() {
        return List.of();
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }
}
