package com.pluralsight.service.interfaces;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.menu.DrinkCategory;
import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<SignatureSandwich> getSignatureSandwiches();

    List<RegularTopping> getRegularToppings();

    List<PremiumToppingsCategory> getPremiumToppingsCategories();

    List<Sauce> getSauces();

    List<DrinkCategory> getDrinkOptions();

    List<Chips> getChipsOptions();

    List<String> getSandwichAvailableModificationTypes();

    List<String> getBreadOptions();

    Map<String, Double> getCustomSandwichPricesBySize();
} 