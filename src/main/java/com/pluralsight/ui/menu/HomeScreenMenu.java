package com.pluralsight.ui.menu;

import com.pluralsight.service.AppCoordinator;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

public class HomeScreenMenu {


    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.NEW_ORDER.getRepresentation(),
                MenuOption.EXIT.getRepresentation()));
    }

    @Getter
    @RequiredArgsConstructor
    public enum MenuOption implements MenuEntry<Void> {
        NEW_ORDER(1, "New order", (Void v) -> AppCoordinator.orderScreenFlow()),
        EXIT(0, "Exit", (Void v) -> AppCoordinator.exit());

        private final int value;
        private final String name;
        private final Consumer<Void> action;

        public String getRepresentation() {
            return value + ". " + name;
        }

        public static int getAmountOfOptions() {
            return values().length;
        }
    }
}
