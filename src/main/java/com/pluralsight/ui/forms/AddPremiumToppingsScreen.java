package com.pluralsight.ui.forms;

import com.pluralsight.model.menu.PremiumToppingsMenu;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.ui.forms.dto.PremiumToppingDto;
import com.pluralsight.ui.forms.dto.SandwichDto;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddPremiumToppingsScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addPremiumToppings(SandwichDto sandwich) {


        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getPremiumToppings(),
                (topping) -> String.format("%s - $%.2f", topping.getName(), topping.getBasePricesBySize(sandwich.getSizeName())),
                "Back"));

        int premiumTopping = ConsoleStringReader.getIntInRangeOfCollection(
                menuRepository.getPremiumToppings(),
                true);
        if (premiumTopping == 0) {
            ScreenUtils.cls();
            return;
        }
        ScreenUtils.cls();
        printSelectedTopping(menuRepository.getPremiumToppings().get(premiumTopping - 1), sandwich);
    }

    private void printSelectedTopping(PremiumToppingsMenu premiumToppingsMenu, SandwichDto sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your " + premiumToppingsMenu.getName());
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                premiumToppingsMenu.getTypes(),
                (type) -> String.format("%s - $%.2f", type, premiumToppingsMenu.getBasePricesBySize(sandwich.getSizeName())),
                "Back"));

        int typeIndex = ConsoleStringReader.getIntInRangeOfCollection(
                premiumToppingsMenu.getTypes(),
                true);
        if (typeIndex == 0) {
            ScreenUtils.cls();
            return;
        }
        String toppingType = premiumToppingsMenu.getTypes().get(typeIndex - 1);

        ScreenUtils.cls();

        boolean isExtraToppings = isExtraToppings(sandwich, premiumToppingsMenu, toppingType);
        PremiumToppingDto premiumToppingDto = PremiumToppingDto.builder()
                .name(premiumToppingsMenu.getName())
                .type(toppingType)
                .basePrice(premiumToppingsMenu.getBasePricesBySize(sandwich.getSizeName()))
                .extraPrice(isExtraToppings ? premiumToppingsMenu.getExtraPriceBySize(sandwich.getSizeName()) : 0.0)
                .isExtra(isExtraToppings)
                .size(sandwich.getSizeName())
                .build();

        sandwich.addPremiumTopping(premiumToppingDto);
    }

    private boolean isExtraToppings(SandwichDto sandwichDto, PremiumToppingsMenu premiumToppingsMenu, String toppingType) {
        ScreenUtils.printBox(List.of(
                "Do you want extra " + toppingType + "? " + "$" +
                        premiumToppingsMenu.getExtraPriceBySize(sandwichDto.getSizeName()),
                "1. Yes",
                "2. No"
        ));
        boolean isExtra = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isExtra;
    }
}
