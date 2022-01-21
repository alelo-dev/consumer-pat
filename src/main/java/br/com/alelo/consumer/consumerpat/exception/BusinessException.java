package br.com.alelo.consumer.consumerpat.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	
	private static final String MESSAGE_ERROR = "Erro de negócio ao efetuar a operação. Verique os dados enviados ou tente novamente mais tarde";
	
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		
	}
	
	@Override
	public String getMessage() {
		return MESSAGE_ERROR;
	}
	
	
}
