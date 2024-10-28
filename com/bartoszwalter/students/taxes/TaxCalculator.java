package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.enums.ContractType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class TaxCalculator {
  public static void main(String[] args) {
    Reader reader = new Reader();
    Output output = new Output();
    Tax taxCalculator = new Tax();

    Map<ContractType, Function<Double, TaxResult>> contractCalculations = new EnumMap<>(ContractType.class);
    contractCalculations.put(ContractType.EMPLOYMENT, income -> taxCalculator.calculateTax(income, true));
    contractCalculations.put(ContractType.CIVIL_CONTRACT, income -> taxCalculator.calculateTax(income, false));

    try {
      double income = reader.getInputIncome();
      ContractType contractType = reader.getContractType();

      Function<Double, TaxResult> taxFunction = contractCalculations.get(contractType);

      if (taxFunction != null) {
        TaxResult result = taxFunction.apply(income);
        output.printTaxDetails(result, contractType);
      } else {
        System.out.println("Unknown contract type!");
      }
    } catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }
}
