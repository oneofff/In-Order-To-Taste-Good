package com.pluralsight.ui.forms;

import com.pluralsight.model.sandwich.Sandwich;
import com.pluralsight.model.sandwich.Topping;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

public class RemoveToppingsScreen {

    public void removeToppings(Sandwich sandwich) {
        while (true) {
            printSandwichToppings(sandwich);
            ScreenUtils.printOnCenterOfTheScreen("Select the topping to remove: ");
            int choice = ConsoleStringReader.getIntInRangeOfCollection(sandwich.getAllToppings(), true);
            if (choice == 0) {
                ScreenUtils.cls();
                return;
            }
            Topping selectedTopping = sandwich.getAllToppings().get(choice - 1);
            sandwich.removeTopping(selectedTopping);
            ScreenUtils.cls();
            ScreenUtils.printOnCenterOfTheScreen(
                    String.format("%s topping removed successfully!", selectedTopping.getName()));
        }
    }

    private void printSandwichToppings(Sandwich sandwich) {
        ScreenUtils.printBox(
                CollectionFormatter.listToMenu(
                        sandwich.getAllToppings(),
                        Topping::getRepresentation,
                        "Back"),
                sandwich.getShortRepresentation());
    }
}
