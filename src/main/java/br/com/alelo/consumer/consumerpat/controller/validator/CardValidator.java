package br.com.alelo.consumer.consumerpat.controller.validator;

import java.util.Objects;

public class CardValidator {

	public static String validateSetBalance(final String cardNumber, final Double value) {

		if (value <= 0) {
			return "Valor não pode ser menor ou igual a 0";
		}
		if (Objects.isNull(value)) {
			return "valor não pode ser vazio";
		}
		if (Objects.isNull(cardNumber)) {
			return "o Numero do  não pode ser null";
		}

		return null;
	}

}
