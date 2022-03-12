package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotNull;

import br.com.alelo.consumer.consumerpat.model.TypeCard;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardVO {

	@NotNull(message = "Required field")
	private Integer cardNumber;

	@NotNull(message = "Required field")
	private Double balance;	

	@NotNull(message = "Required field")
	private TypeCard typeCard;
}
