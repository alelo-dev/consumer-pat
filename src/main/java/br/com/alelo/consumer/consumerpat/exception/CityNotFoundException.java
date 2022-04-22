package br.com.alelo.consumer.consumerpat.exception;

public class CityNotFoundException extends EntityNotFoundException {


	public CityNotFoundException(Long id) {
		super(String.format("Não existe uma cidade com código: %s", id));
	}
	
}
