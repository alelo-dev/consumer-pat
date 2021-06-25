package br.com.alelo.consumer.consumerpat.exception;

/**
 * Exception for Services Erros
 */
public class ServiceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String type;

    /**
     * @param message
     * @param type
     */
    public ServiceException(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
