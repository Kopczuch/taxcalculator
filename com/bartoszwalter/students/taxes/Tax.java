package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.models.TaxResultBuilder;
import com.bartoszwalter.students.taxes.enums.EmploymentRates;
import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class Tax {
    public TaxResult calculateTax(double income, boolean isEmployment) {
        double socialSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.SOCIAL_SECURITY.getRate());
        double healthSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.HEALTH_SECURITY.getRate());
        double sicknessSecurity = CalculationUtils.calculatePercentage(income,
                EmploymentRates.SICKNESS_SECURITY.getRate());

        double taxableIncome = isEmployment ? income : income - (socialSecurity + healthSecurity + sicknessSecurity);

        return calculateCommonTax(taxableIncome, socialSecurity, healthSecurity, sicknessSecurity, isEmployment);
    }

    private TaxResult calculateCommonTax(
            double income,
            double socialSecurity,
            double healthSecurity,
            double sicknessSecurity,
            boolean isEmployment) {

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

        double costs = socialSecurity + healthSecurity + sicknessSecurity + healthSocialTax9 + roundedAdvanceTaxPaid;
        double netIncome = income - costs;

        return new TaxResultBuilder()
                .setIncome(income)
                .setSocialSecurity(socialSecurity)
                .setHealthSecurity(healthSecurity)
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
