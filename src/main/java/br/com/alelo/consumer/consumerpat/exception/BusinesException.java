package br.com.alelo.consumer.consumerpat.exception;

public class BusinesException extends RuntimeException {

	private static final long serialVersionUID = 7986864620634914985L;

	private MessageResponse response;

	public BusinesException(MessageResponse response) {
		this.response = response;
	}

	public MessageResponse getResponse() {
		return response;
	}
}
