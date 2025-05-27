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
        sandwich.setBasePrice(menuRepository.getCustomSandwichPricesBySize().get(sandwich.getSizeName()));
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

    private static boolean isAddToOrder(SandwichDto sandwich) {
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
        ScreenUtils.printBox(toDisplay);
        int size = ConsoleStringReader.getIntInRangeOfCollection(toDisplay, false);
        String sizeName = menuRepository.getCustomSandwichPricesBySize().keySet().toArray()[size - 1].toString();
        ScreenUtils.cls();
        return sizeName;
    }


}
