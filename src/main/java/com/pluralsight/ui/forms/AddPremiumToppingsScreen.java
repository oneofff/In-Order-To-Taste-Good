package com.pluralsight.ui.forms;

import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.model.sandwich.PremiumTopping;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AddPremiumToppingsScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addPremiumToppings(CustomSandwich sandwich) {
        var availablePremiumToppingsCategories = new LinkedList<>(menuRepository.getPremiumToppingsCategories());

        removeAlreadyAddedToppings(availablePremiumToppingsCategories, sandwich);

        while (!availablePremiumToppingsCategories.isEmpty()) {
            PremiumTopping topping = addPremiumToppingFlow(availablePremiumToppingsCategories, sandwich);
            if (topping != null) sandwich.addPremiumTopping(topping);
            else break;
            availablePremiumToppingsCategories.removeIf(category -> category.getToppings().isEmpty());
        }

    }

    private PremiumTopping addPremiumToppingFlow(LinkedList<PremiumToppingsCategory> availablePremiumToppingsCategories, CustomSandwich sandwich) {

        printPremiumToppingsMenu(availablePremiumToppingsCategories, sandwich.getSizeName());

        PremiumToppingsCategory category = getPremiumToppingCategory(availablePremiumToppingsCategories);
        ScreenUtils.cls();
        if (category == null) return null;
        PremiumTopping selectedTopping = getSelectedTopping(category, sandwich);
        if (selectedTopping == null) return null;
        category.getToppings().remove(selectedTopping.getName());
        return selectedTopping;
    }

    private void removeAlreadyAddedToppings(List<PremiumToppingsCategory> availablePremiumToppingsCategories, CustomSandwich sandwich) {
        HashSet<String> alreadyAddedToppings = new HashSet<>();
        for (PremiumTopping topping : sandwich.getPremiumToppings()) {
            alreadyAddedToppings.add(topping.getName());
        }

        availablePremiumToppingsCategories.forEach(category -> {
            category.getToppings().removeIf(alreadyAddedToppings::contains);
        });
        availablePremiumToppingsCategories.removeIf(category -> category.getToppings().isEmpty());
    }

    private PremiumToppingsCategory getPremiumToppingCategory(List<PremiumToppingsCategory> availablePremiumToppingsCategories) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(
                availablePremiumToppingsCategories,
                true);
        return selection == 0 ? null : availablePremiumToppingsCategories.get(selection - 1);
    }

    private void printPremiumToppingsMenu(List<PremiumToppingsCategory> premiumToppingsCategories, String size) {
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumToppingsCategories,
                (topping) -> String.format("%s - $%.2f", topping.getName(), topping.getBasePricesBySize(size)),
                "Back"));
    }

    private PremiumTopping getSelectedTopping(PremiumToppingsCategory premiumToppingsCategory, CustomSandwich sandwich) {

        printToppings(premiumToppingsCategory, sandwich);

        String toppingName = getToppingName(premiumToppingsCategory, sandwich);

        ScreenUtils.cls();

        if (toppingName == null) return null;

        boolean isExtra = getIsExtra(sandwich, premiumToppingsCategory, toppingName);

        return PremiumTopping.builder()
                .category(premiumToppingsCategory.getName())
                .name(toppingName)
                .basePrice(premiumToppingsCategory.getBasePricesBySize(sandwich.getSizeName()))
                .extraPrice(isExtra ? premiumToppingsCategory.getExtraPriceBySize(sandwich.getSizeName()) : 0.0)
                .isExtra(isExtra)
                .size(sandwich.getSizeName())
                .build();
    }

    private static String getToppingName(PremiumToppingsCategory premiumToppingsCategory, CustomSandwich sandwich) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(
                premiumToppingsCategory.getToppings(),
                true

        );
        return selection == 0 ? null : premiumToppingsCategory.getToppings().get(selection - 1);
    }

    private static void printToppings(PremiumToppingsCategory premiumToppingsCategory, CustomSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your " + premiumToppingsCategory.getName());
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumToppingsCategory.getToppings(),
                (type) -> String.format("%s - $%.2f", type, premiumToppingsCategory.getBasePricesBySize(sandwich.getSizeName())),
                "Back"));
    }

    private boolean getIsExtra(CustomSandwich customSandwich, PremiumToppingsCategory premiumToppingsCategory, String toppingType) {
        ScreenUtils.printBox(List.of(
                "Do you want extra " + toppingType + "? " + "$" +
                        premiumToppingsCategory.getExtraPriceBySize(customSandwich.getSizeName()),
                "1. Yes",
                "2. No"
        ));
        boolean isExtra = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isExtra;
    }
}
