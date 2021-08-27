package br.com.alelo.consumer.consumerpat.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {

	private LocalDateTime timestamp;
	private String message;
	
}
