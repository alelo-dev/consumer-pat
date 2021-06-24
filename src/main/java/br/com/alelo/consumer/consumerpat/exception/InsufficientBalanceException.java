package br.com.alelo.consumer.consumerpat.exception;

public class InsufficientBalanceException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super("Insufficient Balance.");
	}

}