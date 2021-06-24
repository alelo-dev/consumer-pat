package br.com.alelo.consumer.consumerpat.model.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMessage {
	
	private int statusCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;

}