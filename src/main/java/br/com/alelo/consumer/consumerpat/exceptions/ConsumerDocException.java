package br.com.alelo.consumer.consumerpat.exceptions;

public class ConsumerDocException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public ConsumerDocException() {

		super("documento já existe");
	}

}
