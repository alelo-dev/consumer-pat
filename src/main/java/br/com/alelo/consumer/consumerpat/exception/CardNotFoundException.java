package br.com.alelo.consumer.consumerpat.exception;

public class CardNotFoundException extends EntityNotFoundException {


	public CardNotFoundException(Long id) {
		super(String.format("Não existe um cartão com código %s", id));
	}
	
}
