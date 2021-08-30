package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CardTypeMismatchException extends RuntimeException {

	private static final long serialVersionUID = -7113869827868170946L;
	
	public CardTypeMismatchException(CardType cardType, CardType establishmentType) {
		super("Mismatch between Card Type["+
				cardType
				+"] and Establishment Type["+
				establishmentType
				+"]!");
	}

}
