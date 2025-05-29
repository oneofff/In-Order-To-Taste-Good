package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomSandwich extends Sandwich {

    @Override
    protected List<PremiumTopping> getAllPremiumToppings() {
        return getPremiumToppings();
    }

    @Override
    protected List<RegularTopping> getAllRegularToppings() {
        return getRegularToppings();
    }

    @Override
    protected List<Sauce> getAllSauces() {
        return getSauces();
    }
}

