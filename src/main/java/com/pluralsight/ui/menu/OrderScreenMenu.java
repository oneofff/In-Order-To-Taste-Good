package com.pluralsight.ui.menu;

import com.pluralsight.model.order.Order;
import com.pluralsight.service.AppCoordinator;
import com.pluralsight.ui.forms.AddChipsScreen;
import com.pluralsight.ui.forms.AddDrinkScreen;
import com.pluralsight.ui.view.OrderSummary;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;

public class OrderScreenMenu {


    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.ADD_SANDWICH.getRepresentation(),
                MenuOption.ADD_DRINKS.getRepresentation(),
                MenuOption.ADD_CHIPS.getRepresentation(),
                MenuOption.CHECKOUT.getRepresentation(),
                MenuOption.CANCEL_ORDER.getRepresentation()));
    }

    @Getter
    @AllArgsConstructor
    public enum MenuOption implements MenuEntry<Order> {
        ADD_SANDWICH(1, "Add sandwich", AppCoordinator::addSandwichFlow),
        ADD_DRINKS(2, "Add drinks", AddDrinkScreen::addDrink),
        ADD_CHIPS(3, "Add chips", AddChipsScreen::addChips),
        CHECKOUT(4, "Checkout", OrderSummary::mock),
        CANCEL_ORDER(0, "Cancel Order", (Order o) -> AppCoordinator.homeScreenFlow());

        private final int value;
        private final String name;
        private final Consumer<Order> action;

        public static int getAmountOfOptions() {
            return MenuOption.values().length;
        }

        public String getRepresentation() {
            return value + ". " + name;
        }
    }
}
