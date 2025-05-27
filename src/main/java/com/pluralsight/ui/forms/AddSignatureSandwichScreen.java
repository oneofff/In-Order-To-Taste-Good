package com.pluralsight.ui.forms;

import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ScreenUtils;

public class AddSignatureSandwichScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addSignatureSandwich() {
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menuRepository.getSignatureSandwiches(),
                (sandwich) -> String.format("%s - $%.2f", sandwich.getName(), sandwich.getPrice()),
                "Back to Main Menu"
        ));
    }
}
