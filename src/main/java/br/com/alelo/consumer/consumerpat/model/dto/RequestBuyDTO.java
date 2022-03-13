package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBuyDTO {

	@NotNull(message = "Required field")
	private EstablishmentDTO establishment;

	@NotNull(message = "Required field")
	private CardValueTransactionDTO cardValueTransaction;

	@NotEmpty(message = "Required field")
	private String productDescription;
}
