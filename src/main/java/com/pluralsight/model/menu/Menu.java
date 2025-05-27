package com.pluralsight.model.menu;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.extras.Drink;
import com.pluralsight.model.sandwich.PremiumTopping;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Menu {
    private List<String> sandwichAvailableModificationTypes;
    private List<SignatureSandwich> signatureSandwiches;
    private Map<String, Double> customSandwichSizePrices;
    private List<String> breadOptions;
    private List<PremiumTopping> premiumToppings;
    private List<RegularTopping> regularToppings;
    private List<Drink> drinkOptions;
    private List<Sauce> sauces;
    private List<Chips> chipsOptions;
}