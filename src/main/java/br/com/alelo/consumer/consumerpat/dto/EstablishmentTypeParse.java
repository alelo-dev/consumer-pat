package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;

public class EstablishmentTypeParse {

    public static CardType parse(int valor){
        CardType retorno = null;
        switch (valor){
            case 1:
                retorno = CardType.FOOD;
            break;
            case 2:
                retorno = CardType.DRUG;
            break;
            case 3:
                retorno = CardType.FUEL;
            break;
        }
        return retorno;
    }
}
