package br.com.alelo.consumer.consumerpat.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 2925547916126190462L;

	public InsufficientBalanceException() {
		super("Insufficient Balance.");
	}

}