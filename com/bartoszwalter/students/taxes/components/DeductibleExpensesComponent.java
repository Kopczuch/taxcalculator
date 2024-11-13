package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class DeductibleExpensesComponent {
    private final TaxConstants percentage = TaxConstants.TAX_DEDUCTIBLE_PERCENTAGE;

    public double calculate(double income) {
        return CalculationUtils.calculatePercentage(income, percentage.getValue());
    }
}
