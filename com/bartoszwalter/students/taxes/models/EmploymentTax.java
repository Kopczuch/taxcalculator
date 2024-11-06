package com.bartoszwalter.students.taxes.models;

import com.bartoszwalter.students.taxes.Tax;

public class EmploymentTax extends Tax {
    public EmploymentTax(double income) {
        super(income);
    }

    @Override
    public double getTaxableIncome() {
        return income;
    }
}
