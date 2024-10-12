package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

  private static final double EMPLOYMENT_SOCIAL_SECURITY_RATE = 9.76;
  private static final double EMPLOYMENT_HEALTH_SECURITY_RATE = 1.5;
  private static final double EMPLOYMENT_SICKNESS_SECURITY_RATE = 2.45;
  private static final double HEALTH_SOCIAL_TAX_RATE_9 = 9.0;
  private static final double HEALTH_SOCIAL_TAX_RATE_7_75 = 7.75;
  private static final double ADVANCE_TAX_RATE = 18.0;
  private static final double TAX_DEDUCTIBLE_PERCENTAGE = 20.0;

  public static void main(String[] args) {
    try {
      InputStreamReader inputStreamReader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      double income = getInputIncome(bufferedReader);
      char contractType = getContactType(bufferedReader);

      if (contractType == 'E') {
          TaxResult result = calculateEmploymentTax(income);
          printTaxDetails(result, "EMPLOYMENT");
      } else if (contractType == 'C') {
          TaxResult result = calculateCivilContractTax(income);
          printTaxDetails(result, "CIVIL CONTRACT");
      } else {
          System.out.println("Unknown type of contract!");
      }

    } catch (Exception ex) {
      System.out.println("Erorr: " + ex.getMessage());
    }
  }

  private static double getInputIncome(BufferedReader bufferedReader) throws Exception {
    System.out.print("Enter income: ");
    return Double.parseDouble(bufferedReader.readLine());
  }

  private static char getContactType(BufferedReader bufferedReader) throws Exception {
    System.out.print("Contract Type: (E)mployment, (C)ivil: ");
    return bufferedReader.readLine().charAt(0);
  }
  
  private static TaxResult calculateEmploymentTax(double income) {
    TaxResult result = new TaxResult();
    result.income = income;
    result.socialSecurity = calculatePercentage(income, EMPLOYMENT_SOCIAL_SECURITY_RATE);
    result.healthSecurity = calculatePercentage(income, EMPLOYMENT_HEALTH_SECURITY_RATE);
    result.sicknessSecurity = calculatePercentage(income, EMPLOYMENT_SICKNESS_SECURITY_RATE);
    result.healthSocialTax9 = calculatePercentage(income, HEALTH_SOCIAL_TAX_RATE_9);
    result.healthSocialTax7_75 = calculatePercentage(income, HEALTH_SOCIAL_TAX_RATE_7_75);
    result.taxDeductibleExpenses = calculateTaxDeductibleExpenses(income);
    result.taxedIncome = income - result.taxDeductibleExpenses;
    result.roundedTaxedIncome = roundToNearestInt(result.taxedIncome);
    result.advanceTax = calculateAdvanceTax(result.roundedTaxedIncome);
    result.taxFreeIncome = calculateTaxFreeIncome();
    result.taxPaid = result.advanceTax - result.taxFreeIncome;
    result.advanceTaxPaid = calculateAdvanceTaxPaid(result.advanceTax, result.healthSocialTax7_75, result.taxFreeIncome);
    result.roundedAdvanceTaxPaid = roundToNearestInt(result.advanceTaxPaid);
    result.netIncome = calculateNetIncome(income, result.socialSecurity, result.healthSecurity, 
                                          result.sicknessSecurity, result.healthSocialTax9, result.roundedAdvanceTaxPaid);
    return result;
  }

  private static TaxResult calculateCivilContractTax(double income) {
    TaxResult result = new TaxResult();
    result.income = income;
    result.socialSecurity = calculatePercentage(income, EMPLOYMENT_SOCIAL_SECURITY_RATE);
    result.healthSecurity = calculatePercentage(income, EMPLOYMENT_HEALTH_SECURITY_RATE);
    result.sicknessSecurity = calculatePercentage(income, EMPLOYMENT_SICKNESS_SECURITY_RATE);
    double adjustedIncome = income - (result.socialSecurity + result.healthSecurity + result.sicknessSecurity);
    result.healthSocialTax9 = calculatePercentage(adjustedIncome, HEALTH_SOCIAL_TAX_RATE_9);
    result.healthSocialTax7_75 = calculatePercentage(adjustedIncome, HEALTH_SOCIAL_TAX_RATE_7_75);
    result.taxDeductibleExpenses = calculateTaxDeductibleExpenses(adjustedIncome);
    result.taxedIncome = adjustedIncome - result.taxDeductibleExpenses;
    result.roundedTaxedIncome = roundToNearestInt(result.taxedIncome);
    result.advanceTax = calculateAdvanceTax(result.roundedTaxedIncome);
    result.taxFreeIncome = 0;
    result.advanceTaxPaid = calculateAdvanceTaxPaid(result.advanceTax, result.healthSocialTax7_75, result.taxFreeIncome);
    result.roundedAdvanceTaxPaid = roundToNearestInt(result.advanceTaxPaid);
    result.netIncome = calculateNetIncome(income, result.socialSecurity, result.healthSecurity, 
                                          result.sicknessSecurity, result.healthSocialTax9, result.roundedAdvanceTaxPaid);
    return result;
  }

  private static void printTaxDetails(TaxResult result, String contractType) {
    System.out.println(contractType);
    System.out.println("Income: " + result.income);
    System.out.println("Social security tax: " + roundToDecimalPlaces(result.socialSecurity));
    System.out.println("Health social security tax: " + roundToDecimalPlaces(result.healthSecurity));
    System.out.println("Sickness social security tax: " + roundToDecimalPlaces(result.sicknessSecurity));
    System.out.println("Health social security tax: 9% = " + roundToDecimalPlaces(result.healthSocialTax9) + 
                        " 7.75% = " + roundToDecimalPlaces(result.healthSocialTax7_75));
    System.out.println("Tax deductible expenses: " + roundToDecimalPlaces(result.taxDeductibleExpenses));
    System.out.println("Income to be taxed: " + roundToDecimalPlaces(result.taxedIncome) + 
                        " rounded: " + result.roundedTaxedIncome);
    System.out.println("Advance tax 18%: " + roundToDecimalPlaces(result.advanceTax));
    System.out.println("Tax free income: " + roundToDecimalPlaces(result.taxFreeIncome));
    System.out.println("Reduced tax: " + roundToDecimalPlaces(result.taxPaid));
    System.out.println("Advance tax paid: " + roundToDecimalPlaces(result.advanceTaxPaid) + 
                        " rounded: " + result.roundedAdvanceTaxPaid);
    System.out.println("\nNet income: " + roundToDecimalPlaces(result.netIncome));
  }

  private static double calculatePercentage(double value, double percentage) {
    return (value * percentage) / 100;
  }

  private static double calculateTaxDeductibleExpenses(double income) {
      return calculatePercentage(income, TAX_DEDUCTIBLE_PERCENTAGE);
  }

  private static double calculateAdvanceTax(double income) {
      return calculatePercentage(income, ADVANCE_TAX_RATE);
  }

  private static double calculateAdvanceTaxPaid(double advanceTax, double healthSocialTax7_75, double taxFreeIncome) {
      return advanceTax - healthSocialTax7_75 - taxFreeIncome;
  }

  private static double calculateNetIncome(double income, double socialSecurity, double healthSecurity, 
                                            double sicknessSecurity, double healthSocialTax9, double advanceTaxPaid) {
      return income - (socialSecurity + healthSecurity + sicknessSecurity + healthSocialTax9 + advanceTaxPaid);
  }

  public static long roundToNearestInt(double value) {
    return Math.round(value);
  }

  public static double roundToDecimalPlaces(double value) {
    return roundToDecimalPlaces(value, 2);
  }

  public static double roundToDecimalPlaces(double value, int decimalPlaces) {
    double scale = Math.pow(10, decimalPlaces);

    return Math.round(value * scale) / scale;
  }

  private static class TaxResult {
    double income;
    double socialSecurity;
    double healthSecurity;
    double sicknessSecurity;
    double healthSocialTax9;
    double healthSocialTax7_75;
    double taxDeductibleExpenses;
    double taxedIncome;
    long roundedTaxedIncome;
    double advanceTax;
    double taxFreeIncome;
    double taxPaid;
    double advanceTaxPaid;
    long roundedAdvanceTaxPaid;
    double netIncome;
  }
}
