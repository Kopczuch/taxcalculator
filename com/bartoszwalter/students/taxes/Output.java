package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.enums.ContractType;

public class Output {
    public void printTaxDetails(TaxResult result, ContractType contractType) {
        System.out.printf(
                """
                        %s
                        Income: %.2f
                        Social Security Tax: %.2f
                        Health Security Tax: %.2f
                        Sickness Security Tax: %.2f
                        Health Social Tax (9%%): %.2f, (7.75%%): %.2f
                        Tax Deductible Expenses: %.2f
                        Taxed Income: %.2f (Rounded: %d)
                        Advance Tax (18%%): %.2f
                        Tax-Free Income: %.2f
                        Advance Tax Paid: %.2f (Rounded: %d)
                        Net Income: %.2f
                        """,
                contractType.getContractType(),
                result.getIncome(),
                result.getSocialSecurity(),
                result.getHealthSecurity(),
                result.getSicknessSecurity(),
                result.getHealthSocialTax9(),
                result.getHealthSocialTax7_75(),
                result.getTaxDeductibleExpenses(),
                result.getTaxedIncome(),
                result.getRoundedTaxedIncome(),
                result.getAdvanceTax(),
                result.getTaxFreeIncome(),
                result.getAdvanceTaxPaid(),
                result.getRoundedAdvanceTaxPaid(),
                result.getNetIncome());
    }
}