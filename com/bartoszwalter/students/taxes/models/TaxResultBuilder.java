package com.bartoszwalter.students.taxes.models;

public class TaxResultBuilder {
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

    public TaxResultBuilder setIncome(double income) {
        this.income = income;
        return this;
    }

    public TaxResultBuilder setSocialSecurity(double socialSecurity) {
        this.socialSecurity = socialSecurity;
        return this;
    }

    public TaxResultBuilder setHealthSecurity(double healthSecurity) {
        this.healthSecurity = healthSecurity;
        return this;
    }

    public TaxResultBuilder setSicknessSecurity(double sicknessSecurity) {
        this.sicknessSecurity = sicknessSecurity;
        return this;
    }

    public TaxResultBuilder setHealthSocialTax9(double healthSocialTax9) {
        this.healthSocialTax9 = healthSocialTax9;
        return this;
    }

    public TaxResultBuilder setHealthSocialTax7_75(double healthSocialTax7_75) {
        this.healthSocialTax7_75 = healthSocialTax7_75;
        return this;
    }

    public TaxResultBuilder setTaxDeductibleExpenses(double taxDeductibleExpenses) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
        return this;
    }

    public TaxResultBuilder setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
        return this;
    }

    public TaxResultBuilder setRoundedTaxedIncome(long roundedTaxedIncome) {
        this.roundedTaxedIncome = roundedTaxedIncome;
        return this;
    }

    public TaxResultBuilder setAdvanceTax(double advanceTax) {
        this.advanceTax = advanceTax;
        return this;
    }

    public TaxResultBuilder setTaxFreeIncome(double taxFreeIncome) {
        this.taxFreeIncome = taxFreeIncome;
        return this;
    }

    public TaxResultBuilder setAdvanceTaxPaid(double advanceTaxPaid) {
        this.advanceTaxPaid = advanceTaxPaid;
        return this;
    }

    public TaxResultBuilder setRoundedAdvanceTaxPaid(long roundedAdvanceTaxPaid) {
        this.roundedAdvanceTaxPaid = roundedAdvanceTaxPaid;
        return this;
    }

    public TaxResultBuilder setNetIncome(double netIncome) {
        this.netIncome = netIncome;
        return this;
    }

    public TaxResult build() {
        return new TaxResult(this);
    }
}
