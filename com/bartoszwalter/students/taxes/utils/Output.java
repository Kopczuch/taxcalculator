package com.bartoszwalter.students.taxes.utils;

import com.bartoszwalter.students.taxes.models.TaxResult;

public class Output {
    public void printTaxDetails(TaxResult result) {
        System.out.println("Tax Details:");
        result.getValues().forEach((key, value) -> System.out.printf("%s: %.2f%n", key, value));
    }
}