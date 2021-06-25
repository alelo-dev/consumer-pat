package br.com.alelo.consumer.consumerpat.view.exception;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.util.Constants;

/**
 * Exception for validations errors
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parâmetros inválidos")
public class DuplicatedRecordException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final List<ErrorData> errors;

	/**
	 * @param fieldsErrors
	 */
	public DuplicatedRecordException(final List<FieldError> fieldsErrors) {
		this(null, null, fieldsErrors);
	}

	/**
	 * @param message
	 * @param fieldsErrors
	 */
	public DuplicatedRecordException(String message, final List<FieldError> fieldsErrors) {
		this(message, null, fieldsErrors);
	}

	/**
	 * @param message
	 * @param cause
	 * @param fieldsErrors
	 */
	public DuplicatedRecordException(String message, Throwable cause, final List<FieldError> fieldsErrors) {
		super(message, cause);
		final List<ErrorData> errorsData = fieldsErrors.stream().map(error -> ErrorData.builder().field(error.getField()).type("Parâmetro inválido").message(error.getDefaultMessage()).build()).collect(Collectors.toList());
		errors = Collections.unmodifiableList(errorsData);
	}

	public List<ErrorData> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		for (final ErrorData error : errors) {
			toString.append(error.toString()).append(Constants.NEW_LINE);
		}
		return toString.toString();
	}
}
