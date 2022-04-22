package br.com.alelo.consumer.consumerpat.exception;

public class StateNotFoundException extends EntityNotFoundException {


	public StateNotFoundException(Long id) {
		super(String.format("Não existe um estado com código: %s", id));
	}
	
}
