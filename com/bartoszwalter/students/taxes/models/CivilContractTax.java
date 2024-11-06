package com.bartoszwalter.students.taxes.models;

import com.bartoszwalter.students.taxes.Tax;

public class CivilContractTax extends Tax {
    public CivilContractTax(double income) {
        super(income);
    }

    @Override
    public double getTaxableIncome() {
        return this.income - (this.socialSecurity + this.healthSecurity + this.sicknessSecurity);
    }
}
