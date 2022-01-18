package br.com.alelo.consumer.consumerpat.exception;

public final class EnvironmentException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EnvironmentException(String message) {
		super(message);
	}

	public EnvironmentException(Throwable rootCause) {
		super(rootCause);
	}



}
