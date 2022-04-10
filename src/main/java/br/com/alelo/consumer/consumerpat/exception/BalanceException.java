package br.com.alelo.consumer.consumerpat.exception;

public class BalanceException extends RuntimeException {

	private static final long serialVersionUID = -5182826215765732867L;

	public BalanceException(String mensagem) {
		super(mensagem);
	}

}
