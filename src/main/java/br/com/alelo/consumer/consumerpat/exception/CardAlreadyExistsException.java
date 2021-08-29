package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CardAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 3059684155890682408L;

	public CardAlreadyExistsException(String cardNumber) {
		super("Card number already exists: " + cardNumber);
	}
	
}
