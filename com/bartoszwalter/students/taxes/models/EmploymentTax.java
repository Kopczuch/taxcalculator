package com.bartoszwalter.students.taxes.models;

import com.bartoszwalter.students.taxes.components.TaxComponent;
import com.bartoszwalter.students.taxes.enums.EmploymentRates;
import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class EmploymentTax implements TaxComponent {
    private final double income;

    public EmploymentTax(double income) {
        this.income = income;
    }

    @Override
    public void calculate(TaxResult result) {
        double socialSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.SOCIAL_SECURITY.getRate());
        double healthSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.HEALTH_SECURITY.getRate());
        double sicknessSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.SICKNESS_SECURITY.getRate());

        double healthSocialTax9 = CalculationUtils.calculatePercentage(income, HealthSocialRates.RATE_9.getRate());
        double healthSocialTax7_75 = CalculationUtils.calculatePercentage(income, HealthSocialRates.RATE_7_75.getRate());

        double taxDeductibleExpenses = CalculationUtils.calculatePercentage(income, TaxConstants.TAX_DEDUCTIBLE_PERCENTAGE.getValue());
        double taxedIncome = income - taxDeductibleExpenses;
        long roundedTaxedIncome = CalculationUtils.roundToNearestInt(taxedIncome);

        double advanceTax = CalculationUtils.calculatePercentage(roundedTaxedIncome, TaxConstants.ADVANCE_TAX_RATE.getValue());
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