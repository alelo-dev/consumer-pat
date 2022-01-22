package br.com.alelo.consumer.consumerpat.exceptions.enums;

import br.com.alelo.consumer.consumerpat.exceptions.BussinesException;

public class EstablishmentTypeNotFoundException extends BussinesException {

    public EstablishmentTypeNotFoundException() {

        super("EstablishmentType inválido");
    }
}
