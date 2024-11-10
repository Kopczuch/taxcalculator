
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
        StringBuilder prompt = new StringBuilder("Contract Type: ");
        for (ContractType type : ContractType.values()) {
            prompt.append("(").append(type.getShortCode()).append(")").append(type.getContractType()).append(", ");
        }
        prompt.setLength(prompt.length() - 2);
        System.out.print(prompt + ": ");

        char choice = reader.readLine().trim().toUpperCase().charAt(0);
        return ContractType.fromShortCode(choice);
    }
}