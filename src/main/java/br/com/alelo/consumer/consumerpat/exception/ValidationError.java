package br.com.alelo.consumer.consumerpat.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private final List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String campo, String mensagens) {
		erros.add(new FieldMessage(campo, mensagens));
	}

}
