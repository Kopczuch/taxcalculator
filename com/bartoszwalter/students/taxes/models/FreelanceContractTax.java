package com.bartoszwalter.students.taxes.models;

import com.bartoszwalter.students.taxes.components.*;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class FreelanceContractTax implements TaxComponent {
    private final double income;

    private final SocialSecurityComponent socialSecurityComponent;
    private final HealthSecurityComponent healthSecurityComponent;
    private final AdvanceTaxComponent advanceTaxComponent;

    public FreelanceContractTax(double income) {
        this.income = income;

        this.socialSecurityComponent = new SocialSecurityComponent();
        this.healthSecurityComponent = new HealthSecurityComponent();
        this.advanceTaxComponent = new AdvanceTaxComponent();
    }

    @Override
    public void calculate(TaxResult result) {
        double socialSecurity = socialSecurityComponent.calculate(income);
        double healthSecurity = healthSecurityComponent.calculate(income);

        double adjustedIncome = income - (socialSecurity + healthSecurity);
        long roundedAdjustedIncome = CalculationUtils.roundToNearestInt(adjustedIncome);
        double advanceTax = advanceTaxComponent.calculate(roundedAdjustedIncome);

        double netIncome = income - (socialSecurity + healthSecurity + advanceTax);

        result.addTaxValue("Income", income);
        result.addTaxValue("SocialSecurity", socialSecurity);
        result.addTaxValue("HealthSecurity", healthSecurity);
        result.addTaxValue("AdjustedIncome", adjustedIncome);
        result.addTaxValue("AdvanceTax", advanceTax);
        result.addTaxValue("NetIncome", netIncome);
    }
}