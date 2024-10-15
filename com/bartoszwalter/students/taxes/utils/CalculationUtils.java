package com.bartoszwalter.students.taxes.utils;

public class CalculationUtils {
    public static double calculatePercentage(double value, double percentage) {
        return (value * percentage) / 100;
    }

    public static long roundToNearestInt(double value) {
        return Math.round(value);
    }
}