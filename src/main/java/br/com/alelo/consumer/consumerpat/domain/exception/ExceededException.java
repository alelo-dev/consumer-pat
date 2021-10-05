package br.com.alelo.consumer.consumerpat.domain.exception;

public class ExceededException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceededException(String msg) {
		super(msg);
	}
}
