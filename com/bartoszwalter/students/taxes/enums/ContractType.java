package com.bartoszwalter.students.taxes.enums;

public enum ContractType {
    EMPLOYMENT("EMPLOYMENT"),
    CIVIL_CONTRACT("CIVIL CONTRACT");

    private final String type;

    ContractType(String type) {
        this.type = type;
    }

    public String getContractType() {
        return type;
    }
}