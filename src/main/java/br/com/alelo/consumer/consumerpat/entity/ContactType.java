package br.com.alelo.consumer.consumerpat.entity;

import java.util.function.Function;

public enum ContactType {
    MobilePhone("MobilePhone",s -> {/* TODO adicionar validação customaizadas */ return true;}),
    ResidencePhone("ResidencePhone"),
    Email("Email");

    public final String contactType;
    private final Function<String,Boolean> isValid;

    ContactType(String contactType, Function<String,Boolean> isValid) {
        this.isValid = isValid;
        this.contactType = contactType;
    }

    ContactType(String contactType) {
        this.contactType = contactType;
        this.isValid = s -> {return true;};
    }
}
