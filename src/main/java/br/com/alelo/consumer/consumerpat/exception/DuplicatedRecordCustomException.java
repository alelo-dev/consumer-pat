package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedRecordCustomException extends CustomException {
	
	private static final long serialVersionUID = 54557269726878859L;

	public DuplicatedRecordCustomException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
