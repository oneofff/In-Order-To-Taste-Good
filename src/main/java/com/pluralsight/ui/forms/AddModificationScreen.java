package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.Sandwich;
import com.pluralsight.repository.FileMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.LinkedList;
import java.util.List;

public class AddModificationScreen {

    private final MenuRepository menuRepository = FileMenuRepository.getInstance();

    public void addModification(Sandwich sandwich) {
        boolean running = true;
        while (running) {
            List<String> modificationMenu = printModificationMenu(sandwich.getShortRepresentation());
            int modificationType = getModificationType(modificationMenu);
            switch (modificationType) {
                case 1 -> new AddPremiumToppingsScreen().addPremiumToppings(sandwich);
                case 2 -> new AddRegularToppingsScreen().addRegularToppings(sandwich);
                case 3 -> new AddSaucesScreen().addSauces(sandwich);
                case 4 -> new RemoveToppingsScreen().removeToppings(sandwich);
                case 0 -> running = false;
                default -> ScreenUtils.printOnCenterOfTheScreen("Invalid option selected.");
            }
        }
    }

    private int getModificationType(List<String> modificationMenu) {
        int modificationType = ConsoleStringReader.getIntInRangeOfCollection(
                modificationMenu, true
        );
        ScreenUtils.cls();
        return modificationType;
    }

    private List<String> printModificationMenu(List<String> sandwichRepresentation) {
        ScreenUtils.printOnCenterOfTheScreen("Please select your modifications");
        List<String> modificationOptions = new LinkedList<>(menuRepository.getSandwichAvailableModificationTypes());
        modificationOptions.add("Remove Toppings");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(modificationOptions
                , String::format,
                "Finish Modifications"
        ), sandwichRepresentation);
        return modificationOptions;
    }
}
