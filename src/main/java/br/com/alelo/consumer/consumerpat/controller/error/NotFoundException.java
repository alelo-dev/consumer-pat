package br.com.alelo.consumer.consumerpat.controller.error;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6626551860446113020L;

	public NotFoundException(String msg) {
		super(msg);
	}

}
