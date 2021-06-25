package br.com.alelo.consumer.consumerpat.view.exception;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = ErrorData.ErrorDataBuilder.class)
@Value
@Builder
public class ErrorData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String field;
	private String message;
	private String type;

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append(field).append(Constants.FIELD_SEPARATOR).append(message);
		return toString.toString();
	}

	// support to Jackson deserialization
	@JsonPOJOBuilder(withPrefix = "")
	public static final class ErrorDataBuilder {
	}
}
