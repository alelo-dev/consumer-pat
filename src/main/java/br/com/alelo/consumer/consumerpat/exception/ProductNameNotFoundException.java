package br.com.alelo.consumer.consumerpat.exception;

public class ProductNameNotFoundException extends EntityNotFoundException {


	public ProductNameNotFoundException(String name) {
		super(String.format("Não existe um produto de nome: %s", name));
	}
	
}
