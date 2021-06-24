package br.com.alelo.consumer.consumerpat.exception;

public class DocumentoNumberAlreadyRegisteredException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public DocumentoNumberAlreadyRegisteredException() {
		super("Document number already registered.");
	}

}