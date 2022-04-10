package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5182826215765732867L;

	public ConsumerNotFoundException(String mensagem) {
		super(mensagem);
	}

}
