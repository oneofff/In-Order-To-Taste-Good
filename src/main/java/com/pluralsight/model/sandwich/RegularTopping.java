package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RegularTopping extends Topping {

    private Double price;

    @Override
    protected double getTotalPrice() {
        return price;
    }

}
