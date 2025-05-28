package com.pluralsight.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.menu.DrinkCategory;
import com.pluralsight.model.menu.Menu;
import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MenuRepository implements IMenuRepository {

    private static final String MENU_JSON_PATH = "data/menu.json";
    private final Menu menu;

    private MenuRepository() {
        menu = loadMenu(MENU_JSON_PATH);
    }

    private static class SingletonHelper {
        private static final MenuRepository INSTANCE = new MenuRepository();
    }

    public static MenuRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Menu getAllMenu() {
        return new Menu(menu);
    }

    @Override
    public List<String> getBreadOptions() {
        return List.copyOf(menu.getBreadOptions());
    }

    @Override
    public Map<String, Double> getCustomSandwichPricesBySize() {
        return Map.copyOf(menu.getCustomSandwichSizePrices());
    }

    @Override
    public List<PremiumToppingsCategory> getPremiumToppingsCategories() {
        return List.copyOf(menu.getPremiumToppingsCategories());
    }

    @Override
    public List<SignatureSandwich> getSignatureSandwiches() {
        return List.copyOf(menu.getSignatureSandwiches());
    }

    @Override
    public List<String> getSandwichAvailableModificationTypes() {
        return List.copyOf(menu.getSandwichAvailableModificationTypes());
    }

    @Override
    public List<RegularTopping> getRegularToppings() {
        return List.copyOf(menu.getRegularToppings());
    }

    @Override
    public List<Sauce> getSauces() {
        return List.copyOf(menu.getSauces());
    }

    @Override
    public List<DrinkCategory> getDrinkOptions() {
        return List.copyOf(menu.getDrinkOptions());
    }

    @Override
    public List<Chips> getChipsOptions() {
        return List.copyOf(menu.getChipsOptions());
    }

    public Menu loadMenu(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(jsonPath), Menu.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu data", e);
        }
    }
}
