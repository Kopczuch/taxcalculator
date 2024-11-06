package com.bartoszwalter.students.taxes.enums;

public enum EmploymentRates {
    SOCIAL_SECURITY(9.76),
    HEALTH_SECURITY(1.5),
    SICKNESS_SECURITY(2.45);

    private final double rate;

    EmploymentRates(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}