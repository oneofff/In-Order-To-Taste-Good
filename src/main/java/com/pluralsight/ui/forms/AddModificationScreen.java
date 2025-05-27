package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddModificationScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addModification(CustomSandwich sandwich) {
        boolean running = true;
        while (running) {
            printModificationMenu(sandwich.getShortRepresentation());
            int modificationType = getModificationType();
            switch (modificationType) {
                case 1 -> new AddPremiumToppingsScreen().addPremiumToppings(sandwich);
                case 2 -> new AddRegularToppingsScreen().addRegularToppings(sandwich);
                case 3 -> new AddSaucesScreen().addSauces(sandwich);
                case 0 -> running = false;
                default -> ScreenUtils.printOnCenterOfTheScreen("Invalid option selected.");
            }
        }
    }

    private int getModificationType() {
        int modificationType = ConsoleStringReader.getIntInRangeOfCollection(
                menuRepository.getSandwichAvailableModificationTypes(), true
        );
        ScreenUtils.cls();
        return modificationType;
    }

    private void printModificationMenu(List<String> sandwichRepresentation) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your modifications");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getSandwichAvailableModificationTypes(), String::format,
                "Finish Modifications"
        ), sandwichRepresentation);
    }
}
