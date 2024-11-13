package com.bartoszwalter.students.taxes.models;

import com.bartoszwalter.students.taxes.components.*;
import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class EmploymentTax implements TaxComponent {
    private final double income;

    private final SocialSecurityComponent socialSecurityComponent;
    private final HealthSecurityComponent healthSecurityComponent;
    private final SicknessSecurityComponent sicknessSecurityComponent;
    private final HighHealthSocialTaxComponent highHealthSocialTaxComponent;
    private final LowHealthSocialTaxComponent lowHealthSocialTaxComponent;
    private final DeductibleExpensesComponent deductibleExpensesComponent;
    private final AdvanceTaxComponent advanceTaxComponent;

    public EmploymentTax(double income) {
        this.income = income;

        this.socialSecurityComponent = new SocialSecurityComponent();
        this.healthSecurityComponent = new HealthSecurityComponent();
        this.sicknessSecurityComponent = new SicknessSecurityComponent();
        this.highHealthSocialTaxComponent = new HighHealthSocialTaxComponent();
        this.lowHealthSocialTaxComponent = new LowHealthSocialTaxComponent();
        this.deductibleExpensesComponent = new DeductibleExpensesComponent();
        this.advanceTaxComponent = new AdvanceTaxComponent();
    }

    @Override
    public void calculate(TaxResult result) {
        double socialSecurity = socialSecurityComponent.calculate(income);
        double healthSecurity = healthSecurityComponent.calculate(income);
        double sicknessSecurity = sicknessSecurityComponent.calculate(income);

        double healthSocialTax9 = highHealthSocialTaxComponent.calculate(income);
        double healthSocialTax7_75 = lowHealthSocialTaxComponent.calculate(income);

        double taxDeductibleExpenses = deductibleExpensesComponent.calculate(income);
        double taxedIncome = income - taxDeductibleExpenses;
        long roundedTaxedIncome = CalculationUtils.roundToNearestInt(taxedIncome);

        double advanceTax = advanceTaxComponent.calculate(roundedTaxedIncome);
        double taxFreeIncome = TaxConstants.TAX_FREE_ALLOWANCE.getValue();

        double advanceTaxPaid = advanceTax - healthSocialTax7_75 - taxFreeIncome;
        long roundedAdvanceTaxPaid = CalculationUtils.roundToNearestInt(advanceTaxPaid);

        double costs = socialSecurity + healthSecurity + sicknessSecurity + healthSocialTax9 + roundedAdvanceTaxPaid;
        double netIncome = income - costs;

        result.addTaxValue("Income", income);
        result.addTaxValue("SocialSecurity", socialSecurity);
        result.addTaxValue("HealthSecurity", healthSecurity);
        result.addTaxValue("SicknessSecurity", sicknessSecurity);
        result.addTaxValue("HealthSocialTax9", healthSocialTax9);
        result.addTaxValue("HealthSocialTax7_75", healthSocialTax7_75);
        result.addTaxValue("TaxDeductibleExpenses", taxDeductibleExpenses);
        result.addTaxValue("TaxedIncome", taxedIncome);
        result.addTaxValue("RoundedTaxedIncome", (double) roundedTaxedIncome);
        result.addTaxValue("AdvanceTax", advanceTax);
        result.addTaxValue("TaxFreeIncome", taxFreeIncome);
        result.addTaxValue("AdvanceTaxPaid", advanceTaxPaid);
        result.addTaxValue("RoundedAdvanceTaxPaid", (double) roundedAdvanceTaxPaid);
        result.addTaxValue("NetIncome", netIncome);
    }
}