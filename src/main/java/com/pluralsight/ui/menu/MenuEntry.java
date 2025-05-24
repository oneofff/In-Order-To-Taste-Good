package com.pluralsight.ui.menu;

public interface MenuEntry {
    int getValue();

    Runnable getAction();
}
