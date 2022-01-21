package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USER_NOT_FOUND_MESSAGE = "Usuário não encontrado";	
	
	@Override
	public String getMessage() {
		return USER_NOT_FOUND_MESSAGE;
	}
	
	
}
