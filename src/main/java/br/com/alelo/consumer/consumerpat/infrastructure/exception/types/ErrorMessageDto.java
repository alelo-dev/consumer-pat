package br.com.alelo.consumer.consumerpat.infrastructure.exception.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("error")
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessageDto implements Serializable {

	private static final long serialVersionUID = -2243742480047312690L;

	private String message;
	private List<ErrorMessageDto> additionalMessages;

	public ErrorMessageDto() {
		super();
		this.additionalMessages = new ArrayList<>();

	}

	public ErrorMessageDto(final String mensagem) {
		super();
		this.message = mensagem;
		this.additionalMessages = new ArrayList<>();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorMessageDto> getErrors() {
		return additionalMessages;
	}

	public void setErrors(List<ErrorMessageDto> errors) {
		this.additionalMessages = errors;
	}

	public void addAdditionalMessages(String addtionalMessage) {
		if (this.additionalMessages == null) {
			this.additionalMessages = new ArrayList<>();
		}

		this.additionalMessages.add(new ErrorMessageDto(addtionalMessage));
	}

	public boolean containsErrors() {
		return this.additionalMessages != null && !this.additionalMessages.isEmpty();
	}

}