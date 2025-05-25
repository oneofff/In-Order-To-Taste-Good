package com.pluralsight.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.model.menu.Menu;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.model.sandwich.Topping;

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
        return this.menu;
    }

    @Override
    public List<String> getBreadOptions() {
        return menu.getBreadOptions();
    }

    @Override
    public Map<String, Double> getSandwichSizes() {
        return menu.getCustomSandwichSizePrices();
    }

    @Override
    public List<Topping> getToppings() {
        return menu.getToppings();
    }

    @Override
    public List<SignatureSandwich> getSignatureSandwiches() {
        return menu.getSignatureSandwiches();
    }

    @Override
    public List<String> getSandwichAvailableModificationTypes() {
        return menu.getSandwichAvailableModificationTypes();
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
