package br.com.alelo.consumer.consumerpat.converter;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 10:01
 */

import br.com.alelo.consumer.consumerpat.exception.IllegalEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.model.enums.AleloCardTypeEnum;

public class EstablishmentToAleloType {

    public static AleloCardTypeEnum convert(Integer value){
        switch (value){
            case 1:
                return AleloCardTypeEnum.FOOD;
            case 2:
                return AleloCardTypeEnum.DRUG;
            case 3:
                return AleloCardTypeEnum.FUEL;
            default:
                throw new IllegalEstablishmentTypeException();
        }
    }

}
