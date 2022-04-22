package br.com.alelo.consumer.consumerpat.exception;

public class RequestNotFoundException extends EntityNotFoundException {


	public RequestNotFoundException(Long id) {
		super(String.format("Não existe um pedido com código %s", id));
	}
	
}
