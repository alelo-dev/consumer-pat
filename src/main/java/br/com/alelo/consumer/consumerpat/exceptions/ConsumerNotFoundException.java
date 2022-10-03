package br.com.alelo.consumer.consumerpat.exceptions;

public class ConsumerNotFoundException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public ConsumerNotFoundException() {

		super("Cliente não encontrado");
	}
}
