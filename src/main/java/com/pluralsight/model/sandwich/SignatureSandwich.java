package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignatureSandwich extends Sandwich {

    private List<RegularTopping> includedRegularToppings;
    private List<PremiumTopping> includedPremiumToppings;
    private List<Sauce> includedSauces;



    @Override
    protected List<PremiumTopping> getAllPremiumToppings() {
        List<PremiumTopping> allPremium = new LinkedList<>();
        if (includedPremiumToppings != null) allPremium.addAll(includedPremiumToppings);
        if (getPremiumToppings() != null) allPremium.addAll(getPremiumToppings());
        return allPremium;
    }

    @Override
    protected List<RegularTopping> getAllRegularToppings() {
        List<RegularTopping> allRegular = new LinkedList<>();
        if (includedRegularToppings != null) allRegular.addAll(includedRegularToppings);
        if (getRegularToppings() != null) allRegular.addAll(getRegularToppings());
        return allRegular;
    }

    @Override
    protected List<Sauce> getAllSauces() {
        List<Sauce> allSauces = new LinkedList<>();
        if (includedSauces != null) allSauces.addAll(includedSauces);
        if (getSauces() != null) allSauces.addAll(getSauces());
        return allSauces;
    }

}
