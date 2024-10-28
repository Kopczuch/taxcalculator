package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.bartoszwalter.students.taxes.enums.ContractType;

public class Reader {
    private BufferedReader reader;

    public Reader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public double getInputIncome() throws Exception {
        System.out.print("Enter income: ");
        return Double.parseDouble(reader.readLine());
    }

    public ContractType getContractType() throws Exception {
        System.out.print("Contract Type: (E)mployment, (C)ivil: ");
        char choice = reader.readLine().trim().toUpperCase().charAt(0);

        return switch (choice) {
            case 'E' -> ContractType.EMPLOYMENT;
            case 'C' -> ContractType.CIVIL_CONTRACT;
            default -> throw new IllegalArgumentException("Invalid contract type entered.");
        };
    }
}