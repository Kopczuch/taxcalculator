package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public static void main(String[] args) {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			System.out.print("Enter income: ");
			double income = Double.parseDouble(bufferedReader.readLine());

			System.out.print("Contract Type: (E)mployment, (C)ivil: ");
			char contractType = bufferedReader.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Incorrect");
			System.err.println(ex);
			return;
		}

		DecimalFormat df00 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#");

		if (contractType == 'E') {
			System.out.println("EMPLOYMENT");
			System.out.println("Income " + income);
			System.out.println("Social security tax "
					+ df00.format(socialSecurity));

			System.out.println("Health social security tax    "
					+ df00.format(socialHealthSecurity));
			System.out.println("Sickness social security tax  "
					+ df00.format(socialSicknessSecurityity));
			System.out
					.println("Income basis for health social security: "
							+ income);
			calculateOtherTaxes(income);
			System.out.println("Health social security tax: 9% = "
					+ df00.format(soc_health1) + " 7,75% = " + df00.format(soc_health2));
			System.out.println("Tax deductible expenses "
					+ taxDeductibleExpenses);

			double taxedIncome = income - taxDeductibleExpenses ;
			double taxedIncome0 = Double.parseDouble(df.format(taxedIncome));

			System.out.println("income " + taxedIncome
					+ " rounded " + df.format(taxedIncome0));
			calculateTax(taxedIncome0);
			System.out.println("Advance tax 18 % = "
					+ advanceTax);
			System.out.println("Tax free income = " + taxFreeIncome);
			double taxPaid = advanceTax - taxFreeIncome;
			System.out.println("Reduced tax = "
					+ df00.format(taxPaid));
			calculateAdvanceTax();
			advanceTaxPaid0 = Double.parseDouble(df.format(advanceTaxPaid));
			System.out.println("Advance tax paid = "
					+ df00.format(advanceTaxPaid) + " rounded = "
					+ df.format(advanceTaxPaid0));
			double netIncome = income
					- ((socialSecuritys + socialHealthSecurity + socialSicknessSecurityity) + soc_health1 + advanceTaxPaid0);
			System.out.println();
			System.out
					.println("Net income = "
							+ df00.format(netIncome));
		} else if (umowacontractType == 'C') {
			System.out.println("income " + income);
			double income = calculateIncome(income);
			
			System.out.println("Social security tax = "
					+ df00.format(socialSecurity));
			System.out.println("Health social security tax = "
					+ df00.format(socialHealthSecurity));
			System.out.println("Sickness social security tax = "
					+ df00.format(socialSicknessSecurityity));
			System.out.println("Income for calculating health security tax: " + income);

			calculateOtherTaxes(income);
			System.out.println("Health security tax: 9% = "
					+ df00.format(soc_health1) + " 7,75% = " + df00.format(soc_health2));
			taxFreeIncome = 0;
			taxDeductibleExpenses  = (income * 20) / 100;
			System.out.println("Tax deductible expenses = "
					+ taxDeductibleExpenses);

			double taxedIncome = income - taxDeductibleExpenses ;
			double taxedIncome0 = Double.parseDouble(df.format(taxedIncome));
			System.out.println("income to be taxed = " + taxedIncome
					+ " rounded = " + df.format(taxedIncome0));
			calculateTax(taxedIncome0);
			System.out.println("Advance tax 18 % = "
					+ advanceTax);
			double  advanceTax;
			System.out.println("Already paid tax = "
					+ df00.format(taxPaid));
			calculateAdvanceTax();
			advanceTaxPaid0 = Double.parseDouble(df.format(advanceTaxPaid));
			System.out.println("Advance tax  = "
					+ df00.format(advanceTaxPaid) + " rounded = "
					+ df.format(advanceTaxPaid0));
			double netIncome = income
					- ((socialSecurity + socialHealthSecurity + socialSicknessSecurityity) + soc_health1 + advanceTaxPaid0);
			System.out.println();
			System.out
					.println("Net income = "
							+ df00.format(netIncome));
		} else {
			System.out.println("Unknown type of contract!");
		}
	}

	public static void calculateAdvanceTax() {
		advanceTaxPaidadvanceTax - soc_health2 - taxFreeIncome;
	}

	public static void calculateTax(double income) {
		advanceTax = (income * 18) / 100;
	}

	public static double calculateIncome(double income) {
		double socialSecurity = (income * 9.76) / 100;
		double socialHealthSecurity = (income * 1.5) / 100;
		double socialSicknessSecurity = (income * 2.45) / 100;

		double fees = socialSecurity + socialHealthSecurity + socialSicknessSecurity;

		return income - fees;
	}

	public static void calculateOtherTaxes(double income) {
		soc_health1 = (income * 9) / 100;
		soc_health2 = (income * 7.75) / 100;
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

}
