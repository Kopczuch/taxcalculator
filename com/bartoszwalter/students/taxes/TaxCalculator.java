package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.bartoszwalter.students.taxes.models.TaxResult;
import com.bartoszwalter.students.taxes.models.TaxResultBuilder;
import com.bartoszwalter.students.taxes.enums.EmploymentRates;
import com.bartoszwalter.students.taxes.enums.HealthSocialRates;
import com.bartoszwalter.students.taxes.enums.TaxConstants;
import com.bartoszwalter.students.taxes.utils.CalculationUtils;

public class TaxCalculator {

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      double income = getInputIncome(reader);
      char contractType = getContractType(reader);

      TaxResult result = switch (contractType) {
        case 'E' -> calculateTax(income, true);
        case 'C' -> calculateTax(income, false);
        default -> {
          System.out.println("Unknown contract type!");
          yield null;
        }
      };

      if (result != null) {
        printTaxDetails(result, contractType == 'E' ? "EMPLOYMENT" : "CIVIL CONTRACT");
      }

    } catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }

  private static double getInputIncome(BufferedReader reader) throws Exception {
    System.out.print("Enter income: ");
    return Double.parseDouble(reader.readLine());
  }

  private static char getContractType(BufferedReader reader) throws Exception {
    System.out.print("Contract Type: (E)mployment, (C)ivil: ");
    return reader.readLine().trim().toUpperCase().charAt(0);
  }

  private static TaxResult calculateTax(double income, boolean isEmployment) {
    double socialSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.SOCIAL_SECURITY.getRate());
    double healthSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.HEALTH_SECURITY.getRate());
    double sicknessSecurity = CalculationUtils.calculatePercentage(income, EmploymentRates.SICKNESS_SECURITY.getRate());

    double taxableIncome = isEmployment ? income : income - (socialSecurity + healthSecurity + sicknessSecurity);

    return calculateCommonTax(taxableIncome, socialSecurity, healthSecurity, sicknessSecurity, isEmployment);
  }

  private static TaxResult calculateCommonTax(
      double income,
      double socialSecurity,
      double healthSecurity,
      double sicknessSecurity,
      boolean isEmployment) {

    // Calculate health social taxes
    double healthSocialTax9 = CalculationUtils.calculatePercentage(income, HealthSocialRates.RATE_9.getRate());
    double healthSocialTax7_75 = CalculationUtils.calculatePercentage(income, HealthSocialRates.RATE_7_75.getRate());

    // Calculate tax deductible expenses and taxable income
    double taxDeductibleExpenses = CalculationUtils.calculatePercentage(income,
        TaxConstants.TAX_DEDUCTIBLE_PERCENTAGE.getValue());
    double taxedIncome = income - taxDeductibleExpenses;

    long roundedTaxedIncome = CalculationUtils.roundToNearestInt(taxedIncome);

    // Calculate advance tax
    double advanceTax = CalculationUtils.calculatePercentage(roundedTaxedIncome,
        TaxConstants.ADVANCE_TAX_RATE.getValue());
    double taxFreeIncome = TaxConstants.TAX_FREE_ALLOWANCE.getValue();

    double advanceTaxPaid = advanceTax - healthSocialTax7_75 - taxFreeIncome;
    long roundedAdvanceTaxPaid = CalculationUtils.roundToNearestInt(advanceTaxPaid);

    // Calculate net income
    double netIncome = income
        - (socialSecurity + healthSecurity + sicknessSecurity + healthSocialTax9 + roundedAdvanceTaxPaid);

    TaxResult taxResult = new TaxResultBuilder()
        .setIncome(income)
        .setSocialSecurity(socialSecurity)
        .setHealthSecurity(healthSecurity)
        .setSicknessSecurity(sicknessSecurity)
        .setHealthSocialTax9(healthSocialTax9)
        .setHealthSocialTax7_75(healthSocialTax7_75)
        .setTaxDeductibleExpenses(taxDeductibleExpenses)
        .setTaxedIncome(taxedIncome)
        .setRoundedTaxedIncome(roundedTaxedIncome)
        .setAdvanceTax(advanceTax)
        .setTaxFreeIncome(taxFreeIncome)
        .setAdvanceTaxPaid(advanceTaxPaid)
        .setRoundedAdvanceTaxPaid(roundedAdvanceTaxPaid)
        .setNetIncome(netIncome)
        .build();

    return taxResult;
  }

  private static void printTaxDetails(TaxResult result, String contractType) {
    System.out.printf(
        """
            %s
            Income: %.2f
            Social Security Tax: %.2f
            Health Security Tax: %.2f
            Sickness Security Tax: %.2f
            Health Social Tax (9%%): %.2f, (7.75%%): %.2f
            Tax Deductible Expenses: %.2f
            Taxed Income: %.2f (Rounded: %d)
            Advance Tax (18%%): %.2f
            Tax-Free Income: %.2f
            Advance Tax Paid: %.2f (Rounded: %d)
            Net Income: %.2f
            """,
        contractType,
        result.income,
        result.socialSecurity,
        result.healthSecurity,
        result.sicknessSecurity,
        result.healthSocialTax9,
        result.healthSocialTax7_75,
        result.taxDeductibleExpenses,
        result.taxedIncome,
        result.roundedTaxedIncome,
        result.advanceTax,
        result.taxFreeIncome,
        result.advanceTaxPaid,
        result.roundedAdvanceTaxPaid,
        result.netIncome);
  }
}
