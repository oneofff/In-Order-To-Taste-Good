package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.PremiumTopping;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddPremiumToppingsScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addPremiumToppings(String sizeName) {


        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getPremiumToppings(),
                (topping) -> String.format("%s - $%.2f", topping.getName(), topping.getBasePricesBySize(sizeName)),
                "Back"));

        int premiumTopping = ConsoleStringReader.getIntInRangeOfCollection(
                menuRepository.getPremiumToppings(),
                true);
        if (premiumTopping == 0) {
            ScreenUtils.cls();
            return;
        }
        ScreenUtils.cls();
        printSelectedTopping(menuRepository.getPremiumToppings().get(premiumTopping - 1), sizeName);
    }

    private void printSelectedTopping(PremiumTopping premiumTopping, String sizeName) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your " + premiumTopping.getName());
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumTopping.getTypes(),
                (type) -> String.format("%s - $%.2f", type, premiumTopping.getBasePricesBySize(sizeName)),
                "Back"));

        int typeIndex = ConsoleStringReader.getIntInRangeOfCollection(
                premiumTopping.getTypes(),
                true);
        if (typeIndex == 0) {
            ScreenUtils.cls();
            return;
        }
        String toppingType = premiumTopping.getTypes().get(typeIndex - 1);

        ScreenUtils.cls();

        boolean extraToppings = isExtraToppings(sizeName, premiumTopping, toppingType);

    }

    private boolean isExtraToppings(String sizeName, PremiumTopping premiumTopping, String toppingType) {
        ScreenUtils.printBox(List.of(
                "Do you want extra " + toppingType + "? " + "$" + premiumTopping.getExtraPriceBySize(sizeName),
                "1. Yes",
                "2. No"
        ));
        boolean isExtra = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isExtra;
    }
}
