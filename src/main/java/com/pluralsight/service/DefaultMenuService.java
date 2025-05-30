package com.pluralsight.service;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.menu.DrinkCategory;
import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.repository.FileMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.service.interfaces.MenuService;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultMenuService implements MenuService {
    private final MenuRepository menuRepository = FileMenuRepository.getInstance();

    @Override
    public List<SignatureSandwich> getSignatureSandwiches() {
        return menuRepository.getSignatureSandwiches();
    }

    @Override
    public List<RegularTopping> getRegularToppings() {
        return menuRepository.getRegularToppings();
    }

    @Override
    public List<PremiumToppingsCategory> getPremiumToppingsCategories() {
        return menuRepository.getPremiumToppingsCategories();
    }

    @Override
    public List<Sauce> getSauces() {
        return menuRepository.getSauces();
    }

    @Override
    public List<DrinkCategory> getDrinkOptions() {
        return menuRepository.getDrinkOptions();
    }

    @Override
    public List<Chips> getChipsOptions() {
        return menuRepository.getChipsOptions();
    }

    @Override
    public List<String> getSandwichAvailableModificationTypes() {
        return menuRepository.getSandwichAvailableModificationTypes();
    }

    @Override
    public List<String> getBreadOptions() {
        return menuRepository.getBreadOptions();
    }

    @Override
    public Map<String, Double> getCustomSandwichPricesBySize() {
        Map<String, Double> raw = menuRepository.getCustomSandwichPricesBySize();
        return raw.entrySet().stream()
                .sorted(Comparator.comparingInt(e ->
                        Integer.parseInt(e.getKey().replaceAll("\\D+", ""))
                ))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
} 