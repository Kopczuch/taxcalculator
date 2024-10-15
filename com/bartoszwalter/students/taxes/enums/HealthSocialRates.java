package com.bartoszwalter.students.taxes.enums;

public enum HealthSocialRates {
    RATE_9(9.0),
    RATE_7_75(7.75);

    private final double rate;

    HealthSocialRates(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}