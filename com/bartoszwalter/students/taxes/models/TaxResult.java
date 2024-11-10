package com.bartoszwalter.students.taxes.models;

import java.util.HashMap;
import java.util.Map;

public class TaxResult {
    private Map<String, Double> taxValues = new HashMap<>();

    public void addTaxValue(String key, double value) {
        taxValues.put(key, value);
    }

    public double getTaxValue(String key) {
        return taxValues.getOrDefault(key, 0.0);
    }

    public Map<String, Double> getValues() {
        return taxValues;
    }
}