package com.pluralsight.repository;

import com.pluralsight.model.menu.Menu;
import com.pluralsight.model.sandwich.PremiumTopping;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.SignatureSandwich;

import java.util.List;
import java.util.Map;

public interface IMenuRepository {
    Menu getAllMenu();

    List<String> getBreadOptions();

    Map<String, Double> getSandwichSizes();

    List<PremiumTopping> getPremiumToppings();

    List<SignatureSandwich> getSignatureSandwiches();

    List<String> getSandwichAvailableModificationTypes();

    List<RegularTopping> getRegularToppings();
}
