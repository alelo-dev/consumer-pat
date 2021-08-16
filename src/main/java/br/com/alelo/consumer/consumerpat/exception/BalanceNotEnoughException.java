package br.com.alelo.consumer.consumerpat.exception;

public class BalanceNotEnoughException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BalanceNotEnoughException(String mensagem) {
		super(mensagem);
	}
}
