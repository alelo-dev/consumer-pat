package br.com.alelo.consumer.consumerpat.exception;

public class BadTypeEstablishmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611539035782501286L;

	
	private static final String MESSAGE = "Tipo de Estabelecimento inválido";
	
	@Override
	public String getMessage() {
		return MESSAGE;
	}
	
}
