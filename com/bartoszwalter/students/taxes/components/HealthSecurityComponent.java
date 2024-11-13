package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.enums.EmploymentRates;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class HealthSecurityComponent {
    private final EmploymentRates rate = EmploymentRates.HEALTH_SECURITY;

    public double calculate(double income) {
        return CalculationUtils.calculatePercentage(income, rate.getRate());
    }
}
