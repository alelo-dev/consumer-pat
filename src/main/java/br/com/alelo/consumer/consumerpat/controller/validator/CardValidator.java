package br.com.alelo.consumer.consumerpat.controller.validator;

import java.util.Objects;

public class CardValidator {

    public static String validateSetBalance(final String cardNumber, final Double value) {

        if (Objects.isNull(cardNumber)) {
            return "cardNumber não pode ser null";
        }
        if (Objects.isNull(value)) {
            return "value não pode ser null";
        }
        if (value <= 0) {
            return "value não pode ser menor ou igual a 0";
        }
        return null;
    }
}
