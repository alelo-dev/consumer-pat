package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CompanyType {

    FOOD(1, "Alimentação"),
    DRUGSTORE(2, "Farmácia"),
    FUEL(3, "Posto de combustível");

    private final Integer code;
    private final String name;


    public static CompanyType getCompanyTypeById(final Integer code) {
        for (CompanyType type : CompanyType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CompanyType code: " + code);
    }
}



