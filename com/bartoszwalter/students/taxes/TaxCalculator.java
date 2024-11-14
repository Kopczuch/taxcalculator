
package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.components.TaxComponent;
import com.bartoszwalter.students.taxes.enums.ContractType;
import com.bartoszwalter.students.taxes.factory.TaxComponentFactory;
import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.utils.Output;
import com.bartoszwalter.students.taxes.utils.Reader;

public class TaxCalculator {
    public static void main(String[] args) {
        Reader reader = new Reader();
        Output output = new Output();

        try {
            double income = reader.getInputIncome();
            ContractType contractType = reader.getContractType();

            TaxResult result = new TaxResult();
            TaxComponent taxComponent = TaxComponentFactory.createTaxComponent(contractType, income);
            taxComponent.calculate(result);

            output.printTaxDetails(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}