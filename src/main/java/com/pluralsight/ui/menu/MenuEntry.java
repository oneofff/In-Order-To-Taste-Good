package com.pluralsight.ui.menu;

import java.util.function.Consumer;

public interface MenuEntry<T> {
    int getValue();

    Consumer<T> getAction();
}
