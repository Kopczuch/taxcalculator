package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.models.TaxResultBuilder;
import com.bartoszwalter.students.taxes.enums.EmploymentRates;
import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public abstract class Tax {
    protected final double income;

    protected final double socialSecurity;
    protected final double healthSecurity;
    protected final double sicknessSecurity;

    public Tax(double income) {
        this.income = income;

        this.socialSecurity = CalculationUtils.calculatePercentage(this.income, EmploymentRates.SOCIAL_SECURITY.getRate());
        this.healthSecurity = CalculationUtils.calculatePercentage(this.income, EmploymentRates.HEALTH_SECURITY.getRate());
        this.sicknessSecurity = CalculationUtils.calculatePercentage(this.income, EmploymentRates.SICKNESS_SECURITY.getRate());
    }

    public TaxResult calculateTax() {
        double taxableIncome = getTaxableIncome();

        return calculateCommonTax(taxableIncome);
    }

    protected abstract double getTaxableIncome();

    private TaxResult calculateCommonTax(double income) {

        double healthSocialTax9 = CalculationUtils.calculatePercentage(income, HealthSocialRates.RATE_9.getRate());
        double healthSocialTax7_75 = CalculationUtils.calculatePercentage(income,
                HealthSocialRates.RATE_7_75.getRate());

        double taxDeductibleExpenses = CalculationUtils.calculatePercentage(income,
                TaxConstants.TAX_DEDUCTIBLE_PERCENTAGE.getValue());
        double taxedIncome = income - taxDeductibleExpenses;

        long roundedTaxedIncome = CalculationUtils.roundToNearestInt(taxedIncome);

        double advanceTax = CalculationUtils.calculatePercentage(roundedTaxedIncome,
                TaxConstants.ADVANCE_TAX_RATE.getValue());
        double taxFreeIncome = TaxConstants.TAX_FREE_ALLOWANCE.getValue();

        double advanceTaxPaid = advanceTax - healthSocialTax7_75 - taxFreeIncome;
        long roundedAdvanceTaxPaid = CalculationUtils.roundToNearestInt(advanceTaxPaid);

        double costs = this.socialSecurity + this.healthSecurity + this.sicknessSecurity + healthSocialTax9 + roundedAdvanceTaxPaid;
        double netIncome = income - costs;

        return new TaxResultBuilder()
                .setIncome(income)
                .setSocialSecurity(this.socialSecurity)
                .setHealthSecurity(this.healthSecurity)
                .setSicknessSecurity(sicknessSecurity)
                .setHealthSocialTax9(healthSocialTax9)
                .setHealthSocialTax7_75(healthSocialTax7_75)
                .setTaxDeductibleExpenses(taxDeductibleExpenses)
                .setTaxedIncome(taxedIncome)
                .setRoundedTaxedIncome(roundedTaxedIncome)
                .setAdvanceTax(advanceTax)
                .setTaxFreeIncome(taxFreeIncome)
                .setAdvanceTaxPaid(advanceTaxPaid)
                .setRoundedAdvanceTaxPaid(roundedAdvanceTaxPaid)
                .setNetIncome(netIncome)
                .build();
    }
}
