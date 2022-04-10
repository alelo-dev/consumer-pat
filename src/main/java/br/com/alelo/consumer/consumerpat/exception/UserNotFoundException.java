package br.com.alelo.consumer.consumerpat.exception;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
