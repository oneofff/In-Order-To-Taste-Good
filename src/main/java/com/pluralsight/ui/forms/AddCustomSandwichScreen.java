package com.pluralsight.ui.forms;

import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddCustomSandwichScreen {
    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addCustomSandwich() {
        String sizeName = selectSandwichSize();
        int breadType = selectBreadType();

        new AddModificationScreen().addModification(sizeName);
    }


    private int selectBreadType() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your bread");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getBreadOptions(),
                (bread) -> String.format("%s", bread)
        ));
        int breadType = ConsoleStringReader.getIntInRangeOfCollection(menuRepository.getBreadOptions(), false);
        ScreenUtils.cls();
        return breadType;
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
