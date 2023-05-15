package br.com.alelo.consumer.consumerpat.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDefaultResponse {
	private Date timestamp;
	private String message;
	private String details;
}