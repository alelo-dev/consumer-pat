package br.com.alelo.consumer.consumerpat.exception;

public class ProductNotFoundException extends EntityNotFoundException {


	public ProductNotFoundException(Long id) {
		super(String.format("Não existe um produto com código %s", id));
	}
	
}
