package com.pluralsight.ui.forms;

import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.ui.forms.dto.SandwichDto;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddCustomSandwichScreen {
    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addCustomSandwich() {
        SandwichDto sandwich = buildSandwich();

        boolean addToOrder = isAddToOrder(sandwich);

        if (addToOrder) {

            ScreenUtils.printOnCenterOfTheScreen("Your custom sandwich has been added to your order.");

        } else {
            ScreenUtils.cls();
            ScreenUtils.printOnCenterOfTheScreen("Your custom sandwich was not added to your order.");
        }
    }

    private SandwichDto buildSandwich() {
        SandwichDto sandwich = new SandwichDto();

        sandwich.setSizeName(selectSandwichSize());
        sandwich.setBasePrice(menuRepository.getSandwichSizes().get(sandwich.getSizeName()));
        sandwich.setBread(selectBread());

        new AddModificationScreen().addModification(sandwich);
        return sandwich;
    }

    private static boolean isAddToOrder(SandwichDto sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Your custom sandwich");
        ScreenUtils.printBox(sandwich.getRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Do you want to add this sandwich to your order? (1/0)");
        boolean isAdd = ConsoleStringReader.getIntInRangeWithMargin(0, 1) == 1;
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
        List<String> toDisplay = CollectionFormatter.mapToIndexedList(menuRepository.getSandwichSizes());
        ScreenUtils.printBox(toDisplay);
        int size = ConsoleStringReader.getIntInRangeOfCollection(toDisplay, false);
        String sizeName = menuRepository.getSandwichSizes().keySet().toArray()[size - 1].toString();
        ScreenUtils.cls();
        return sizeName;
    }


}
