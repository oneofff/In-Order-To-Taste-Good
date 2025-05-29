package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.RegularTopping;
import com.pluralsight.model.sandwich.Sandwich;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.repository.FileMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AddRegularToppingsScreen {

    private final MenuRepository menuRepository = FileMenuRepository.getInstance();

    public void addRegularToppings(Sandwich sandwich) {
        List<RegularTopping> availableRegularToppings = new LinkedList<>(menuRepository.getRegularToppings());

        removeAlreadyAddedToppings(availableRegularToppings, sandwich);

        while (!availableRegularToppings.isEmpty()) {
            RegularTopping regularTopping = addRegularToppingFlow(availableRegularToppings, sandwich);
            if (regularTopping != null) sandwich.addRegularTopping(regularTopping);
            else break;
        }


    }

    private RegularTopping addRegularToppingFlow(List<RegularTopping> availableRegularToppings, Sandwich sandwich) {
        printAvailableRegularToppings(availableRegularToppings, sandwich.getShortRepresentation());

        RegularTopping selectedTopping = getSelectedTopping(availableRegularToppings);
        ScreenUtils.cls();

        if (selectedTopping == null) return null;

        availableRegularToppings.remove(selectedTopping);
        return selectedTopping;
    }

    private RegularTopping getSelectedTopping(List<RegularTopping> availableRegularToppings) {
        int selection = ConsoleStringReader.getIntInRangeOfCollection(availableRegularToppings, true);
        if (selection == 0) return null;

        return availableRegularToppings.get(selection - 1);
    }

    private static void printAvailableRegularToppings(List<RegularTopping> availableRegularToppings, List<String> sandwichRepresentation) {
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                availableRegularToppings, RegularTopping::getRepresentation,
                "Back"
        ), sandwichRepresentation);
    }

    private void removeAlreadyAddedToppings(List<RegularTopping> availableRegularToppings, Sandwich sandwich) {
        HashSet<String> alreadyAddedToppings = new HashSet<>();
        for (RegularTopping topping : sandwich.getRegularToppings()) {
            alreadyAddedToppings.add(topping.getName());
        }

        if (sandwich instanceof SignatureSandwich signatureSandwich) {
            alreadyAddedToppings.addAll(signatureSandwich.getIncludedRegularToppings().stream()
                    .map(RegularTopping::getName)
                    .toList());
        }

        availableRegularToppings.removeIf(topping -> alreadyAddedToppings.contains(topping.getName()));
    }
}
