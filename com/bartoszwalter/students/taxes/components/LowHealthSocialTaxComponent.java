package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class LowHealthSocialTaxComponent {
    private final HealthSocialRates rate = HealthSocialRates.RATE_7_75;

    public double calculate(double income) {
        return CalculationUtils.calculatePercentage(income, rate.getRate());
    }
}
