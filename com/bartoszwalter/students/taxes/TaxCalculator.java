package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.CivilContractTax;
import com.bartoszwalter.students.taxes.models.EmploymentTax;
import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.enums.ContractType;

import java.util.EnumMap;
import java.util.Map;

public class TaxCalculator {
    public static void main(String[] args) {
        Reader reader = new Reader();
        Output output = new Output();

        Map<ContractType, Class<? extends Tax>> taxClasses = new EnumMap<>(ContractType.class);
        taxClasses.put(ContractType.EMPLOYMENT, EmploymentTax.class);
        taxClasses.put(ContractType.CIVIL_CONTRACT, CivilContractTax.class);

        try {
            double income = reader.getInputIncome();
            ContractType contractType = reader.getContractType();

            Class<? extends Tax> taxClass = taxClasses.get(contractType);

            if (taxClass != null) {
                Tax tax = taxClass.getDeclaredConstructor(double.class).newInstance(income);
                TaxResult result = tax.calculateTax();
                output.printTaxDetails(result, contractType);
            } else {
                System.out.println("Unknown contract type!");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
