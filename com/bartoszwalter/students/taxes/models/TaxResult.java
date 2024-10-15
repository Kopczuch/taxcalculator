package com.bartoszwalter.students.taxes.models;

public class TaxResult {

    public double income;
    public double socialSecurity;
    public double healthSecurity;
    public double sicknessSecurity;
    public double healthSocialTax9;
    public double healthSocialTax7_75;
    public double taxDeductibleExpenses;
    public double taxedIncome;
    public long roundedTaxedIncome;
    public double advanceTax;
    public double taxFreeIncome;
    public double advanceTaxPaid;
    public long roundedAdvanceTaxPaid;
    public double netIncome;

    public TaxResult(TaxResultBuilder builder) {
        this.income = builder.income;
        this.socialSecurity = builder.socialSecurity;
        this.healthSecurity = builder.healthSecurity;
        this.sicknessSecurity = builder.sicknessSecurity;
        this.healthSocialTax9 = builder.healthSocialTax9;
        this.healthSocialTax7_75 = builder.healthSocialTax7_75;
        this.taxDeductibleExpenses = builder.taxDeductibleExpenses;
        this.taxedIncome = builder.taxedIncome;
        this.roundedTaxedIncome = builder.roundedTaxedIncome;
        this.advanceTax = builder.advanceTax;
        this.taxFreeIncome = builder.taxFreeIncome;
        this.advanceTaxPaid = builder.advanceTaxPaid;
        this.roundedAdvanceTaxPaid = builder.roundedAdvanceTaxPaid;
        this.netIncome = builder.netIncome;
    }
}
