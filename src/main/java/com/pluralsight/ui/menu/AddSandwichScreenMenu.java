package com.pluralsight.ui.menu;

import com.pluralsight.model.order.Order;
import com.pluralsight.service.AppCoordinator;
import com.pluralsight.ui.forms.AddCustomSandwichScreen;
import com.pluralsight.ui.forms.AddSignatureSandwichScreen;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class AddSandwichScreenMenu {
    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.ADD_CUSTOM_SANDWICH.getRepresentation(),
                MenuOption.ADD_SIGNATURE_SANDWICH.getRepresentation(),
                MenuOption.BACK.getRepresentation()));
    }

    @Getter
    public enum MenuOption implements MenuEntry<Order> {
        ADD_CUSTOM_SANDWICH(1, "Add custom sandwich", new AddCustomSandwichScreen()::addCustomSandwich),
        ADD_SIGNATURE_SANDWICH(2, "Add signature sandwich", new AddSignatureSandwichScreen()::addSignatureSandwich),
        BACK(0, "Back", (Order o) -> AppCoordinator.orderScreenFlow());
        private final int value;
        private final String name;
        private final Consumer<Order> action;

        MenuOption(int value, String name, Consumer<Order> action) {
            this.value = value;
            this.name = name;
            this.action = action;
        }

        public String getRepresentation() {
            return value + ". " + name;
        }

        public static int getAmountOfOptions() {
            return values().length;
        }
    }
}
