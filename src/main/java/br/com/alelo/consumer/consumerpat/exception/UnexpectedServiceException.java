package br.com.alelo.consumer.consumerpat.exception;

/**
 * Exception for unexpected Error that should be mapped to a 500 status
 */
public class UnexpectedServiceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexpectedServiceException(String message) {
        super(message);
    }

    public UnexpectedServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
