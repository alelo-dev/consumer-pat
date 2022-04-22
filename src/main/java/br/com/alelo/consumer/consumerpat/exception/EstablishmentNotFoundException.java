package br.com.alelo.consumer.consumerpat.exception;

public class EstablishmentNotFoundException extends EntityNotFoundException {


	public EstablishmentNotFoundException(Long id) { super(String.format("Não existe um estabelecimento com código %s", id));
	}
	
}
