package com.pluralsight.utils.DataTypes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BankBigDecimal {
    private final BigDecimal value;
    public static final BankBigDecimal ONE = new BankBigDecimal(1);
    public static final MathContext DEFAULT_CONTEXT = new MathContext(10, RoundingMode.HALF_EVEN);

    public BankBigDecimal(String value) {
        this.value = new BigDecimal(value);
    }

    public BankBigDecimal(BigDecimal value) {
        this.value = value;
    }

    public BankBigDecimal(int value) {
        this.value = new BigDecimal(value);
    }

    public BankBigDecimal(double value) {
        this.value = new BigDecimal(value);
    }

    public BankBigDecimal divide(BankBigDecimal divisor) {
        return new BankBigDecimal(this.value.divide(divisor.value, DEFAULT_CONTEXT));
    }

    public BankBigDecimal divide(int divisor) {
        return new BankBigDecimal(this.value.divide(BigDecimal.valueOf(divisor), DEFAULT_CONTEXT));
    }

    public BankBigDecimal multiply(BankBigDecimal multiplicand) {
        return new BankBigDecimal(this.value.multiply(multiplicand.value, DEFAULT_CONTEXT));
    }

    public BankBigDecimal multiply(int multiplicand) {
        return new BankBigDecimal(this.value.multiply(BigDecimal.valueOf(multiplicand), DEFAULT_CONTEXT));
    }

    public BankBigDecimal add(BankBigDecimal augend) {
        return new BankBigDecimal(this.value.add(augend.value, DEFAULT_CONTEXT));
    }

    public BankBigDecimal add(int augend) {
        return new BankBigDecimal(this.value.add(BigDecimal.valueOf(augend), DEFAULT_CONTEXT));
    }

    public BankBigDecimal subtract(BankBigDecimal subtrahend) {
        return new BankBigDecimal(this.value.subtract(subtrahend.value, DEFAULT_CONTEXT));
    }

    public BankBigDecimal subtract(int subtrahend) {
        return new BankBigDecimal(this.value.subtract(BigDecimal.valueOf(subtrahend), DEFAULT_CONTEXT));
    }

    public Double getDoubleValue() {
        return this.value.doubleValue();
    }

    public BankBigDecimal pow(int exponent) {
        return new BankBigDecimal(this.value.pow(exponent, DEFAULT_CONTEXT));
    }

    public BankBigDecimal setScale(int scale) {
        return new BankBigDecimal(this.value.setScale(scale, RoundingMode.HALF_EVEN));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BankBigDecimal add(BigDecimal one) {
        return new BankBigDecimal(this.value.add(one, DEFAULT_CONTEXT));
    }

    public BankBigDecimal subtract(BigDecimal one) {
        return new BankBigDecimal(this.value.subtract(one, DEFAULT_CONTEXT));
    }

    public int compareTo(BankBigDecimal amount) {
        return this.value.compareTo(amount.value);
    }
}