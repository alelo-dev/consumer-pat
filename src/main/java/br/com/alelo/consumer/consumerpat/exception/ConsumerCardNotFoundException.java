package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerCardNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public ConsumerCardNotFoundException() {
		super("Consumer Card not found.");
	}

}