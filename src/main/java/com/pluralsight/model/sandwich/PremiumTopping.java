package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PremiumTopping extends Topping {

    private List<String> types;
    private Map<String, Double> basePricesBySize;
    private Map<String, Double> extraPricesBySize;


    @Override
    public double getPrice(String size) {
        return basePricesBySize.getOrDefault(size, 0.0) + extraPricesBySize.getOrDefault(size, 0.0);
    }

    public double getBasePricesBySize(String size) {
        return basePricesBySize.getOrDefault(size, 0.0);
    }

    public double getExtraPriceBySize(String size) {
        return extraPricesBySize.getOrDefault(size, 0.0);
    }
}
