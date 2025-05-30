package com.pluralsight.ui.forms;

import com.pluralsight.model.menu.PremiumToppingsCategory;
import com.pluralsight.model.sandwich.PremiumTopping;
import com.pluralsight.model.sandwich.Sandwich;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.service.DefaultMenuService;
import com.pluralsight.service.interfaces.MenuService;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AddPremiumToppingsScreen {
    private final MenuService menuService = new DefaultMenuService();

    public void addPremiumToppings(Sandwich sandwich) {
        var availablePremiumToppingsCategories = new LinkedList<>(menuService.getPremiumToppingsCategories());

        removeAlreadyAddedToppings(availablePremiumToppingsCategories, sandwich);

        while (!availablePremiumToppingsCategories.isEmpty()) {
            PremiumTopping topping = addPremiumToppingFlow(availablePremiumToppingsCategories, sandwich);
            if (topping != null) sandwich.addPremiumTopping(topping);
            else break;
            availablePremiumToppingsCategories.removeIf(category -> category.getToppings().isEmpty());
        }

    }

    private PremiumTopping addPremiumToppingFlow(LinkedList<PremiumToppingsCategory> availablePremiumToppingsCategories, Sandwich sandwich) {

        printPremiumToppingsMenu(availablePremiumToppingsCategories, sandwich.getSize(), sandwich);

        PremiumToppingsCategory category = getPremiumToppingCategory(availablePremiumToppingsCategories);
        ScreenUtils.cls();
        if (category == null) return null;
        PremiumTopping selectedTopping = getSelectedTopping(category, sandwich);
        if (selectedTopping == null) return null;
        category.getToppings().remove(selectedTopping.getName());
        return selectedTopping;
    }

    private void removeAlreadyAddedToppings(List<PremiumToppingsCategory> availablePremiumToppingsCategories, Sandwich sandwich) {
        HashSet<String> alreadyAddedToppings = new HashSet<>();
        for (PremiumTopping topping : sandwich.getPremiumToppings()) {
            alreadyAddedToppings.add(topping.getName());
        }
        if (sandwich instanceof SignatureSandwich signatureSandwich) {
            alreadyAddedToppings.addAll(signatureSandwich.getIncludedPremiumToppings().stream()
                    .map(PremiumTopping::getName)
                    .toList());
        }

        availablePremiumToppingsCategories.forEach(category -> category.getToppings().removeIf(alreadyAddedToppings::contains));
        availablePremiumToppingsCategories.removeIf(category -> category.getToppings().isEmpty());

    }

    private PremiumToppingsCategory getPremiumToppingCategory(List<PremiumToppingsCategory> availablePremiumToppingsCategories) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(
                availablePremiumToppingsCategories,
                true);
        return selection == 0 ? null : availablePremiumToppingsCategories.get(selection - 1);
    }

    private void printPremiumToppingsMenu(List<PremiumToppingsCategory> premiumToppingsCategories, String size, Sandwich sandwich) {
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumToppingsCategories,
                (topping) -> String.format("%s - $%.2f", topping.getCategory(), topping.getBasePricesBySize(size)),
                "Back"), sandwich.getShortRepresentation());
    }

    private PremiumTopping getSelectedTopping(PremiumToppingsCategory premiumToppingsCategory, Sandwich sandwich) {

        printToppings(premiumToppingsCategory, sandwich);

        String toppingName = getToppingName(premiumToppingsCategory);

        ScreenUtils.cls();

        if (toppingName == null) return null;

        boolean isExtra = getIsExtra(sandwich, premiumToppingsCategory, toppingName);

        return PremiumTopping.builder()
                .category(premiumToppingsCategory.getCategory())
                .name(toppingName)
                .basePrice(premiumToppingsCategory.getBasePricesBySize(sandwich.getSize()))
                .extraPrice(isExtra ? premiumToppingsCategory.getExtraPriceBySize(sandwich.getSize()) : 0.0)
                .isExtra(isExtra)
                .size(sandwich.getSize())
                .build();
    }

    private static String getToppingName(PremiumToppingsCategory premiumToppingsCategory) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(
                premiumToppingsCategory.getToppings(),
                true

        );
        return selection == 0 ? null : premiumToppingsCategory.getToppings().get(selection - 1);
    }

    private static void printToppings(PremiumToppingsCategory premiumToppingsCategory, Sandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your " + premiumToppingsCategory.getCategory());
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumToppingsCategory.getToppings(),
                (type) -> String.format("%s - $%.2f", type, premiumToppingsCategory.getBasePricesBySize(sandwich.getSize())),
                "Back"), sandwich.getShortRepresentation());
    }

    private boolean getIsExtra(Sandwich customSandwich, PremiumToppingsCategory premiumToppingsCategory, String toppingType) {
        ScreenUtils.printBox(List.of(
                "Do you want extra " + toppingType + "? " + "$" +
                        premiumToppingsCategory.getExtraPriceBySize(customSandwich.getSize()),
                "1. Yes",
                "2. No"
        ), customSandwich.getShortRepresentation());
        boolean isExtra = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isExtra;
    }
}
