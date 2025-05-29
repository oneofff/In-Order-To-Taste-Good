package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RegularTopping extends Topping {

    @Override
    protected double getTotalPrice() {
        return getBasePrice();
    }


    public String getRepresentation() {
        return String.format("%s %s", getName(), getBasePrice() == 0.0 ? "" : String.format(" -$%.2f", getBasePrice()));
    }
}
