package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNameNotFoundException extends EntityNotFoundException {


	public ConsumerNameNotFoundException(String name) {
		super(String.format("Não existe um consumidor de nome: %s", name));
	}
	
}
