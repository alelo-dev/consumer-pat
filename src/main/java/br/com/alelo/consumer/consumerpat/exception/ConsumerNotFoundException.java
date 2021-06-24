package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public ConsumerNotFoundException() {
		super("Consumer not found.");
	}

}