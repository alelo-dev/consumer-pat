package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardValueTransactionDTO {

	@NotNull(message = "Required field")
	private Integer cardNumber;
	
	@NotNull(message = "Required field")
	private Double value;
}
