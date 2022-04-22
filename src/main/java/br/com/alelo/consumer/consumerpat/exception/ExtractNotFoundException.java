package br.com.alelo.consumer.consumerpat.exception;

public class ExtractNotFoundException extends EntityNotFoundException {


	public ExtractNotFoundException(Long id) {
		super(String.format("Não existe um extrato com código %s", id));
	}
	
}
