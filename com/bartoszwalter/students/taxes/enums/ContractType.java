package com.bartoszwalter.students.taxes.enums;

import com.bartoszwalter.students.taxes.components.TaxComponent;
import com.bartoszwalter.students.taxes.models.CivilContractTax;
import com.bartoszwalter.students.taxes.models.EmploymentTax;
import com.bartoszwalter.students.taxes.models.FreelanceContractTax;

public enum ContractType {
    EMPLOYMENT("EMPLOYMENT", 'E', EmploymentTax.class),
    CIVIL_CONTRACT("CIVIL CONTRACT", 'C', CivilContractTax.class),
    FREELANCE("FREELANCE", 'F', FreelanceContractTax.class);

    private final String type;
    private final char shortCode;
    private final Class<? extends TaxComponent> taxClass;

    ContractType(String type, char shortCode, Class<? extends TaxComponent> taxClass) {
        this.type = type;
        this.shortCode = shortCode;
        this.taxClass = taxClass;
    }

    public String getContractType() {
        return type;
    }

    public char getShortCode() {
        return shortCode;
    }

    public Class<? extends TaxComponent> getTaxClass() {
        return taxClass;
    }

    public static ContractType fromShortCode(char code) {
        for (ContractType type : values()) {
            if (type.shortCode == Character.toUpperCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid contract type code: " + code);
    }
}