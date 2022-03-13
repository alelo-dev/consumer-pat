package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotNull;

import br.com.alelo.consumer.consumerpat.model.enums.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

	@NotNull(message = "Required field")
	private Integer cardNumber;

	@NotNull(message = "Required field")
	private Double balance;	

	@NotNull(message = "Required field")
	private TypeCard typeCard;
}
