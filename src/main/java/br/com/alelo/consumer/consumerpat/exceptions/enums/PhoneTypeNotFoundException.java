package br.com.alelo.consumer.consumerpat.exceptions.enums;

import br.com.alelo.consumer.consumerpat.exceptions.BussinesException;

public class PhoneTypeNotFoundException extends BussinesException {

    public PhoneTypeNotFoundException() {

        super("PhoneType inválido");
    }
}
