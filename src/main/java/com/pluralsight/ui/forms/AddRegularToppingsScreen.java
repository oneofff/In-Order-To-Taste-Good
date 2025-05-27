package com.pluralsight.ui.forms;

import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.ui.forms.dto.SandwichDto;
import com.pluralsight.utils.console.ScreenUtils;

public class AddRegularToppingsScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addRegularToppings(SandwichDto sandwichDto) {

        ScreenUtils.printBox(menuRepository.getRegularToppings()
                .stream()
                .map(topping -> topping.getName() + " - $" + topping.getPrice())
                .toList());
    }
}
