package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.Sandwich;
import com.pluralsight.model.sandwich.Sauce;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.service.DefaultMenuService;
import com.pluralsight.service.interfaces.MenuService;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.LinkedList;
import java.util.List;

public class AddSaucesScreen {
    private final MenuService menuService = new DefaultMenuService();

    public void addSauces(Sandwich sandwich) {
        List<Sauce> sauces = new LinkedList<>(menuService.getSauces());
        removeAlreadyAddedSauces(sauces, sandwich);

        while (!sauces.isEmpty()) {
            printAvailableSauces(sauces, sandwich.getShortRepresentation());
            Sauce selectedSauce = getSelectedSauce(sauces);
            if (selectedSauce == null) break;

            sandwich.addSauce(selectedSauce);
            sauces.remove(selectedSauce);
            ScreenUtils.cls();
        }

        ScreenUtils.cls();
    }

    private Sauce getSelectedSauce(List<Sauce> sauces) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(sauces, true);
        if (selection == 0) return null;

        return sauces.get(selection - 1);
    }

    private void removeAlreadyAddedSauces(List<Sauce> sauces, Sandwich sandwich) {
        sauces.removeIf(sauce -> sandwich.getSauces().stream()
                .anyMatch(addedSauce -> addedSauce.getName().equals(sauce.getName())));

        if (sandwich instanceof SignatureSandwich signatureSandwich) {
            sauces.removeIf(sauce -> signatureSandwich.getIncludedSauces().stream()
                    .anyMatch(addedSauce -> addedSauce.getName().equals(sauce.getName())));
        }
    }

    private void printAvailableSauces(List<Sauce> sauces, List<String> sandwichRepresentation) {
        ScreenUtils.printOnCenterOfTheScreen("Available Sauces:");
        ScreenUtils.printBox(
                CollectionFormatter.listToMenu(
                        sauces,
                        Sauce::getRepresentation,
                        "Back"
                )
                , sandwichRepresentation);
    }
}
