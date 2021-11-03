package br.com.alelo.consumer.consumerpat.exception;

public final class UnsupportedOperationException extends RuntimeException {
	
	public UnsupportedOperationException(String mensagem) {
		super(mensagem);
	}
	
	public UnsupportedOperationException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}