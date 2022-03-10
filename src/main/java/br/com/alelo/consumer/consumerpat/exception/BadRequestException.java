package br.com.alelo.consumer.consumerpat.exception;

public class BadRequestException extends RuntimeException {

    /**
	 * @author raul.candido
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
