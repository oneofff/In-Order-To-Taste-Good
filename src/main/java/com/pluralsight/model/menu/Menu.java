package com.pluralsight.model.menu;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.extras.Drink;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Menu {
    private List<String> sandwichAvailableModificationTypes;
    private List<SignatureSandwich> signatureSandwiches;
    private Map<String, Double> customSandwichSizePrices;
    private List<String> breadOptions;
    private List<PremiumToppingsCategory> premiumToppingsCategories;
    private List<RegularTopping> regularToppings;
    private List<Drink> drinkOptions;
    private List<Sauce> sauces;
    private List<Chips> chipsOptions;

    public Menu(Menu menu) {
        this.sandwichAvailableModificationTypes = menu.getSandwichAvailableModificationTypes();
        this.signatureSandwiches = menu.getSignatureSandwiches();
        this.customSandwichSizePrices = menu.getCustomSandwichSizePrices();
        this.breadOptions = menu.getBreadOptions();
        this.premiumToppingsCategories = menu.getPremiumToppingsCategories();
        this.regularToppings = menu.getRegularToppings();
        this.drinkOptions = menu.getDrinkOptions();
        this.sauces = menu.getSauces();
        this.chipsOptions = menu.getChipsOptions();
    }
}