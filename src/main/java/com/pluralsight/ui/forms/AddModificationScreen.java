package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

public class AddModificationScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addModification(CustomSandwich sandwich) {

        boolean running = true;
        while (running) {
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
        printModificationMenu();
        int modificationType = ConsoleStringReader.getIntInRangeOfCollection(
                menuRepository.getSandwichAvailableModificationTypes(), true
        );
        ScreenUtils.cls();
        return modificationType;
    }

    private void printModificationMenu() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your modifications");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getSandwichAvailableModificationTypes(), String::format,
                "Finish Modifications"
        ));
    }
}
