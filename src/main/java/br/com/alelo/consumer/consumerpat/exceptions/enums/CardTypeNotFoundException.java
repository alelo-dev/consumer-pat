package br.com.alelo.consumer.consumerpat.exceptions.enums;

import br.com.alelo.consumer.consumerpat.exceptions.BussinesException;

public class CardTypeNotFoundException extends BussinesException {

    public CardTypeNotFoundException() {

        super("CardType inválido");
    }
}
