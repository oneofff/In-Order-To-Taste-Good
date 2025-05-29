package com.pluralsight.model.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PremiumToppingsCategory {
    private String category;
    private List<String> toppings;
    private Map<String, Double> basePricesBySize;
    private Map<String, Double> extraPricesBySize;

    public double getBasePricesBySize(String size) {
        return basePricesBySize.getOrDefault(size, 0.0);
    }

    public double getExtraPriceBySize(String size) {
        return extraPricesBySize.getOrDefault(size, 0.0);
    }
}
