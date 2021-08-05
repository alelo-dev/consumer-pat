package br.com.alelo.consumer.consumerpat.exception;

public enum MessageCode {

	ERRO_INESPERADO("E001", 500), CONSUMER_NOT_FOUND("E010", 400), CONSUMER_ALREADY_EXISTS("E020", 400),
	CARD_NOT_FOUND("E030", 400), CARD_ALREADY_EXISTS("E040", 400), INSUFFICIENT_FUNDS("E050", 400),
	INVALID_VALUE("E060", 400), MULTIPLE_CARD_NUMBER("E060", 400), CARD_NOT_INFORMED("E070", 400);

	private final String code;

	private final Integer status;

	MessageCode(final String code, final Integer status) {
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return code;
	}
}
