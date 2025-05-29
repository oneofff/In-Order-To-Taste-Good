package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.service.SandwichService;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.Comparator;
import java.util.List;

public class AddCustomSandwichScreen {
    private final IMenuRepository menuRepository = MenuRepository.getInstance();
    private final SandwichService sandwichService = new SandwichService();

    public void addCustomSandwich(Order order) {
        CustomSandwich sandwich = buildSandwich();

        boolean addToOrder = isAddToOrder(sandwich);
        ScreenUtils.cls();
        if (addToOrder) {
            sandwichService.addCustomSandwichToOrder(order, sandwich);
            ScreenUtils.printOnCenterOfTheScreen("Your sandwich was added.");
            ScreenUtils.printBox(order.getOrderRepresentation());
        } else {
            ScreenUtils.printOnCenterOfTheScreen("Your sandwich was not added to the order.");
            ScreenUtils.printBox(sandwich.getRepresentation());
        }
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private CustomSandwich buildSandwich() {
        CustomSandwich sandwich = new CustomSandwich();
        sandwich.setName("Custom Sandwich");
        sandwich.setSize(selectSandwichSize());
        sandwich.setBasePrice(menuRepository.getCustomSandwichPricesBySize().get(sandwich.getSize()));
        sandwich.setBread(selectBread());
        sandwich.setToasted(getIsToasted());

        new AddModificationScreen().addModification(sandwich);
        return sandwich;
    }

    private boolean getIsToasted() {
        ScreenUtils.printBox(List.of(
                "Do you want your sandwich toasted?",
                "1 - Yes",
                "2 - No"
        ));
        boolean isToasted = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isToasted;
    }

    private static boolean isAddToOrder(CustomSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Your custom sandwich");
        ScreenUtils.printBox(sandwich.getRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");
        boolean isAdd = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isAdd;
    }


    private String selectBread() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your bread");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getBreadOptions(),
                (bread) -> String.format("%s", bread)
        ));
        int breadType = ConsoleStringReader.getIntInRangeOfCollection(menuRepository.getBreadOptions(), false);
        ScreenUtils.cls();
        return menuRepository.getBreadOptions().get(breadType - 1);
    }

    private String selectSandwichSize() {
        ScreenUtils.printOnCenterOfTheScreen("Please select sandwich size: ");
        List<String> toDisplay = CollectionFormatter.mapToIndexedList(menuRepository.getCustomSandwichPricesBySize());
        //sort the list to display
        toDisplay.sort(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D+", ""))));
        ScreenUtils.printBox(toDisplay);
        int size = ConsoleStringReader.getIntInRangeOfCollection(toDisplay, false);
        String sizeName = menuRepository.getCustomSandwichPricesBySize().keySet().toArray()[size - 1].toString();
        ScreenUtils.cls();
        return sizeName;
    }

}
