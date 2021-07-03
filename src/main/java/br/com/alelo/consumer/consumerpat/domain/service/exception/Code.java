package br.com.alelo.consumer.consumerpat.domain.service.exception;


public enum Code {
    INVALID_REFUND("invalid.refund", "Insuficient Refund"),
    INVALID_EXCEPTION("invalid.exception", "Retry again later"),
    INVALID_NOT_FOUND("invalid.not.found", "Not Found"),
    INVALID_UPDATE("invalid.update", "Cant update");

    Code(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
