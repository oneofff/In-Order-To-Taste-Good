package com.pluralsight.ui.forms;

import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.ScreenUtils;

public class AddSignatureSandwichScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addSignatureSandwich() {
        ScreenUtils.printBox(menuRepository.getSignatureSandwiches()
                .stream()
                .map(sandwich -> sandwich.getName() + " - $" + sandwich.getPrice())
                .toList());
    }
}
