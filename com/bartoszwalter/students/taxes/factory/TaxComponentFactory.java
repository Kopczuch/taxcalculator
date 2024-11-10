package com.bartoszwalter.students.taxes.factory;

import com.bartoszwalter.students.taxes.components.TaxComponent;
import com.bartoszwalter.students.taxes.enums.ContractType;

public class TaxComponentFactory {
    public static TaxComponent createTaxComponent(ContractType contractType, double income) {
        try {
            return contractType.getTaxClass()
                    .getConstructor(double.class)
                    .newInstance(income);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create tax component for " + contractType, e);
        }
    }
}