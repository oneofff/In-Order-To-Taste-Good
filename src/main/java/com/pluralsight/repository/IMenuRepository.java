package com.pluralsight.repository;

import com.pluralsight.model.menu.DrinkCategory;
import com.pluralsight.model.menu.Menu;
import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;

import java.util.List;
import java.util.Map;

public interface IMenuRepository {
    Menu getAllMenu();

    List<String> getBreadOptions();

    Map<String, Double> getCustomSandwichPricesBySize();

    List<PremiumToppingsCategory> getPremiumToppingsCategories();

    List<SignatureSandwich> getSignatureSandwiches();

    List<String> getSandwichAvailableModificationTypes();

    List<RegularTopping> getRegularToppings();

    List<Sauce> getSauces();

    List<DrinkCategory> getDrinkOptions();
}
