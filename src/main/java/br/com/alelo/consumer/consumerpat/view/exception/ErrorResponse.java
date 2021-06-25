package br.com.alelo.consumer.consumerpat.view.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Singular;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Encapsula as informações de erro, que serão retornadas quando houver alguma
 * exceção em um serviço REST disponibilizado pela aplicação.
 *
 */
@JsonDeserialize(builder = ErrorResponse.ErrorBuilder.class)
@Value
@AllArgsConstructor
public class ErrorResponse {

	private String statusCode;

	@Singular
	private List<ErrorData> errors;

	/**
	 * @param statusCode
	 * @param type
	 * @param message
	 */
	public ErrorResponse(String statusCode, String type, String message) {
		final ErrorData errorData = ErrorData.builder().type(type).message(message).build();
		this.statusCode = statusCode;
		this.errors = Collections.unmodifiableList(Arrays.asList(errorData));
	}

	// support to Jackson deserialization
	@JsonPOJOBuilder(withPrefix = "")
	public static final class ErrorBuilder {
	}
}
