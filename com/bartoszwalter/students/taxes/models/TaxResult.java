package com.bartoszwalter.students.taxes.models;

public class TaxResult {

    private double income;
    private double socialSecurity;
    private double healthSecurity;
    private double sicknessSecurity;
    private double healthSocialTax9;
    private double healthSocialTax7_75;
    private double taxDeductibleExpenses;
    private double taxedIncome;
    private long roundedTaxedIncome;
    private double advanceTax;
    private double taxFreeIncome;
    private double advanceTaxPaid;
    private long roundedAdvanceTaxPaid;
    private double netIncome;

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

    public double getIncome() {
        return income;
    }

    public double getSocialSecurity() {
        return socialSecurity;
    }

    public double getHealthSecurity() {
        return healthSecurity;
    }

    public double getSicknessSecurity() {
        return sicknessSecurity;
    }

    public double getHealthSocialTax9() {
        return healthSocialTax9;
    }

    public double getHealthSocialTax7_75() {
        return healthSocialTax7_75;
    }

    public double getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public long getRoundedTaxedIncome() {
        return roundedTaxedIncome;
    }

    public double getAdvanceTax() {
        return advanceTax;
    }

    public double getTaxFreeIncome() {
        return taxFreeIncome;
    }

    public double getAdvanceTaxPaid() {
        return advanceTaxPaid;
    }

    public long getRoundedAdvanceTaxPaid() {
        return roundedAdvanceTaxPaid;
    }

    public double getNetIncome() {
        return netIncome;
    }
}
