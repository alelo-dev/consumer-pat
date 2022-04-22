package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends EntityNotFoundException {


	public ConsumerNotFoundException(Long id) {
		super(String.format("Não existe um consumidor com código: %s", id));
	}
	
}
