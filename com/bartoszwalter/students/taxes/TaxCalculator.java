package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.enums.ContractType;

public class TaxCalculator {
  public static void main(String[] args) {
    Reader reader = new Reader();
    Output output = new Output();
    Tax taxCalculator = new Tax();

    try {
      double income = reader.getInputIncome();
      ContractType contractType = reader.getContractType();

      TaxResult result = switch (contractType) {
        case EMPLOYMENT -> taxCalculator.calculateTax(income, true);
        case CIVIL_CONTRACT -> taxCalculator.calculateTax(income, false);
        default -> {
          System.out.println("Unknown contract type!");
          yield null;
        }
      };

      if (result != null) {
        output.printTaxDetails(result, contractType);
      }
    } catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }
}
