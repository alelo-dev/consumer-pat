package br.com.alelo.consumer.consumerpat.exception;

/**
 * Classe para tratametno de exceções de negocio
 * @author julio.silva
 */
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
        super(message);
    }

}
