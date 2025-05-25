package com.pluralsight.repository;

import com.pluralsight.model.menu.Menu;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.model.sandwich.Topping;

import java.util.List;
import java.util.Map;

public interface IMenuRepository {
    Menu getAllMenu();

    List<String> getBreadOptions();

    Map<String, Double> getSandwichSizes();

    List<Topping> getToppings();

    List<SignatureSandwich> getSignatureSandwiches();

    List<String> getSandwichAvailableModificationTypes();
}
