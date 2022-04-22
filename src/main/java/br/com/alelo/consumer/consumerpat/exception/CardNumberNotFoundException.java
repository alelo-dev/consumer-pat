package br.com.alelo.consumer.consumerpat.exception;

public class CardNumberNotFoundException extends EntityNotFoundException {


	public CardNumberNotFoundException(Integer number) {
		super(String.format("Não existe um cartão com número %s", number));
	}
	
}
