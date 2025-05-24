package com.pluralsight.ui.menu;

import com.pluralsight.service.AppCoordinator;
import com.pluralsight.utils.console.ScreenUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class HomeScreenMenu {


    public static int amountOfOptions() {
        return MenuOption.getAmountOfOptions();
    }

    public static void print() {
        ScreenUtils.printBox(List.of(MenuOption.NEW_ORDER.getRepresentation(),
                MenuOption.EXIT.getRepresentation()));
    }

    @Getter
    @AllArgsConstructor
    public enum MenuOption implements MenuEntry {
        NEW_ORDER(1, "New order", AppCoordinator::orderScreenFlow),
        EXIT(0, "Exit", AppCoordinator::exit);

        private final int value;
        private final String name;
        private final Runnable action;

        public String getRepresentation() {
            return value + ". " + name;
        }

        public static int getAmountOfOptions() {
            return values().length;
        }
    }
}
