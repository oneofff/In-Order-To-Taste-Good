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

        int modification = selectModification();


//        ScreenUtils.printBox(List.of(
//                "Do you want extra meat: " + "$" + menuRepository.getToppings().get(0).getPrice(sizeName)
//                , "1. Yes"
//                , " 2. No"));
//        boolean extraMeat = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
//        ScreenUtils.cls();
//        ScreenUtils.printBox(List.of(
//                "Do you want extra cheese: " + "$" + menuRepository.getToppings().get(1).getPrice(sizeName)
//                , "1. Yes"
//                , "2. No"));
//
//        boolean extraCheese = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
//        ScreenUtils.cls();
//        ScreenUtils.printBox(List.of(
//                "Do you want extra toppings: " + "$" + menuRepository.getToppings().get(2).getPrice(sizeName)
//                , menuRepository.getToppings().get(2).getName() + " - " + menuRepository.getToppings().get(2).getPrice(sizeName)
//        ));
//        ScreenUtils.printlnWithMargins("Do you want extra toppings: ");
//        boolean extraToppings = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
//        ScreenUtils.printlnWithMargins("Select sauces: ");
//        int sauces = ConsoleStringReader.getIntInRangeWithMargin(1, 5);
//        ScreenUtils.printlnWithMargins("Would you like the sandwich toasted? ");
//        boolean toasted = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
//        //TODO return SANDWICH
    }

    private int selectModification() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your modifications");
        ScreenUtils.printBox(CollectionFormatter.listToIndexedList(menuRepository.getSandwichAvailableModificationTypes()));
        int modificationType = ConsoleStringReader.getIntInRangeWithMargin(1, 5);
        ScreenUtils.cls();
        return modificationType;
    }

    private int selectBreadType() {
        ScreenUtils.printOnCenterOfTheScreen("Please select your bread");
        ScreenUtils.printBox(CollectionFormatter.listToIndexedList(menuRepository.getBreadOptions()));
        int breadType = ConsoleStringReader.getIntInRangeWithMargin(1, 5);
        ScreenUtils.cls();
        return breadType;
    }

    private String selectSandwichSize() {
        ScreenUtils.printOnCenterOfTheScreen("Please select sandwich size: ");
        List<String> toDisplay = CollectionFormatter.mapToIndexedList(menuRepository.getSandwichSizes());
        ScreenUtils.printBox(toDisplay);
        int size = ConsoleStringReader.getIntInRangeWithMargin(1, 3);
        String sizeName = menuRepository.getSandwichSizes().keySet().toArray()[size - 1].toString();
        ScreenUtils.cls();
        return sizeName;
    }


}
