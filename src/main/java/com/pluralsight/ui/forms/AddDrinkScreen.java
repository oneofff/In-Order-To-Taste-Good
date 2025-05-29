package com.pluralsight.ui.forms;

import com.pluralsight.model.extras.Drink;
import com.pluralsight.model.menu.DrinkCategory;
import com.pluralsight.model.order.Order;
import com.pluralsight.repository.FileMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddDrinkScreen {
    private final MenuRepository menuRepository = FileMenuRepository.getInstance();

    public void addDrink(Order order) {

        List<DrinkCategory> drinkOptions = menuRepository.getDrinkOptions();

        if (drinkOptions == null || drinkOptions.isEmpty()) {
            ScreenUtils.printOnCenterOfTheScreen("Sorry, no drinks are available at the moment.");
            ScreenUtils.waitTillPressEnter();
            ScreenUtils.cls();
            return;
        }

        ScreenUtils.printOnCenterOfTheScreen("Choose a drink to add: ");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                drinkOptions,
                drink -> String.format("%s", drink.getName())
        ));

        int drinkChoice = ConsoleStringReader.getIntInRangeOfCollection(drinkOptions, false);
        DrinkCategory selectedDrink = drinkOptions.get(drinkChoice - 1);
        ScreenUtils.cls();

        ScreenUtils.printOnCenterOfTheScreen("Choose a size for your  " + selectedDrink.getName() + ":");
        ScreenUtils.printBox(CollectionFormatter.mapToIndexedList(
                selectedDrink.getSizePrices()
        ));

        int sizeChoice = ConsoleStringReader.getIntInRangeOfCollection(drinkOptions, false);
        String size = selectedDrink.getSizePrices().keySet().stream()
                .toList()
                .get(sizeChoice - 1);

        Drink drinkToAdd = new Drink();
        drinkToAdd.setName(selectedDrink.getName());
        drinkToAdd.setSize(size);
        drinkToAdd.setPrice(selectedDrink.getSizePrices().get(size));

        ScreenUtils.printBox(drinkToAdd.getRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");

        order.addItem(drinkToAdd);
        ScreenUtils.cls();
        ScreenUtils.printOnCenterOfTheScreen(String.format("%s drink added successfully!", drinkToAdd.getName())); //
        ScreenUtils.printBox(order.getOrderRepresentation());
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }
}