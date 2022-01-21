package br.com.alelo.consumer.consumerpat.exception;

public abstract class ConsumerException extends RuntimeException {

	private static final long serialVersionUID = -4175761837286179566L;

	public ConsumerException() {
		this(null, null);
	}
	
	public ConsumerException(final String message) {
		super(message);
	}
	
	public ConsumerException(final Throwable cause) {
		super(cause);
	}
	
	public ConsumerException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
