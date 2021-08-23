package br.com.alelo.consumer.consumerpat.util;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;

public class ResponseUtil {
	public static ResponseEntity<?> getBadRequestResponse(BusinessException e) {
		ResponseEntity<?> output;
		HashMap<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		output = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		return output;
	}

	public static ResponseEntity<?> getInternalErrorResponse() {
		ResponseEntity<?> output;
		HashMap<String, Object> body = new HashMap<>();
		body.put("message", "An unknown error has occurred");
		output = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		return output;
	}
}
