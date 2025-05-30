package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.service.DefaultMenuService;
import com.pluralsight.service.interfaces.MenuService;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddCustomSandwichScreen {
    private final MenuService menuService = new DefaultMenuService();

    public void addCustomSandwich(Order order) {
        CustomSandwich sandwich = buildSandwich();
        boolean addToOrder = confirmAddition(sandwich);
        ScreenUtils.cls();

        if (addToOrder) {
            order.addItem(sandwich);
            showAdded(order);
        } else {
            showCancelled(sandwich);
        }

        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private CustomSandwich buildSandwich() {
        CustomSandwich sandwich = new CustomSandwich();
        sandwich.setName("Custom Sandwich");
        sandwich.setSize(selectSize());
        sandwich.setBasePrice(getBasePrice(sandwich.getSize()));
        sandwich.setBread(selectBread());
        sandwich.setToasted(selectToasted());
        new AddModificationScreen().addModification(sandwich);
        return sandwich;
    }

    private String selectSize() {
        ScreenUtils.printOnCenterOfTheScreen("Please select sandwich size:");
        List<String> menu = CollectionFormatter.mapToIndexedList(
                menuService.getCustomSandwichPricesBySize()
        );
        ScreenUtils.printBox(menu);

        int choice = ConsoleStringReader.getIntInRangeOfCollection(menu, false);
        String sizeKey = menuService.getCustomSandwichPricesBySize()
                .keySet().toArray()[choice - 1].toString();
        ScreenUtils.cls();
        return sizeKey;
    }

    private double getBasePrice(String size) {
        return menuService.getCustomSandwichPricesBySize().get(size);
    }

    private String selectBread() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your bread");
        List<String> options = menuService.getBreadOptions();
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                options,
                bread -> bread
        ));
        int choice = ConsoleStringReader.getIntInRangeOfCollection(options, false);
        ScreenUtils.cls();
        return options.get(choice - 1);
    }

    private boolean selectToasted() {
        ScreenUtils.printBox(List.of(
                "Do you want your sandwich toasted?",
                "1 - Yes",
                "2 - No"
        ));
        boolean toasted = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return toasted;
    }

    private boolean confirmAddition(CustomSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Your custom sandwich");
        ScreenUtils.printBox(sandwich.getRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");
        return ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
    }

    private void showAdded(Order order) {
        ScreenUtils.printOnCenterOfTheScreen("Your sandwich was added.");
        ScreenUtils.printBox(order.getOrderRepresentation());
    }

    private void showCancelled(CustomSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen(
                "Your sandwich was not added to the order."
        );
        ScreenUtils.printBox(sandwich.getRepresentation());
    }
}
