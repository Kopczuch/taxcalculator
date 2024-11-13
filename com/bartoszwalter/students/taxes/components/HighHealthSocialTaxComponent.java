package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class HighHealthSocialTaxComponent {
    private final HealthSocialRates rate = HealthSocialRates.RATE_9;

    public double calculate(double income) {
        return CalculationUtils.calculatePercentage(income, rate.getRate());
    }
}
