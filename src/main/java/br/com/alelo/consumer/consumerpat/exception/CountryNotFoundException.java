package br.com.alelo.consumer.consumerpat.exception;

public class CountryNotFoundException extends EntityNotFoundException {


	public CountryNotFoundException(Long id) {
		super(String.format("Não existe um país com código: %s", id));
	}
	
}
