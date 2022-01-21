package br.com.alelo.consumer.consumerpat.exception;

public class BadCardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static final String MESSSGE = "Tipo de Cartão ou número inválido";
	
	@Override
	public String getMessage() {
		return MESSSGE;
	}
	
	
}
