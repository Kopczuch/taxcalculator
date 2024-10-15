package com.bartoszwalter.students.taxes.enums;

public enum TaxConstants {
    ADVANCE_TAX_RATE(18.0),
    TAX_DEDUCTIBLE_PERCENTAGE(20.0),
    TAX_FREE_ALLOWANCE(0);

    private final double value;

    TaxConstants(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
