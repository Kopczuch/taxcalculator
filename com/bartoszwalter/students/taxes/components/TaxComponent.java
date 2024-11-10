package com.bartoszwalter.students.taxes.components;

import com.bartoszwalter.students.taxes.models.TaxResult;

public interface TaxComponent {
    void calculate(TaxResult result);
}