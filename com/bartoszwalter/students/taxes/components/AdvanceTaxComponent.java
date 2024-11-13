package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class AdvanceTaxComponent {
    private final TaxConstants rate = TaxConstants.ADVANCE_TAX_RATE;

    public double calculate(double roundedTaxIncome) {
        return CalculationUtils.calculatePercentage(roundedTaxIncome, rate.getValue());
    }
}
