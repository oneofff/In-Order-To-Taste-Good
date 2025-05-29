package com.pluralsight.model.menu;

import lombok.Data;

import java.util.Map;

@Data
public class DrinkCategory {
    private String name;
    private Map<String, Double> sizePrices = Map.of();
}
