package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotNull;

import br.com.alelo.consumer.consumerpat.model.enums.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {

	@NotNull(message = "Required field")
	private TypeCard typeCard;
	
	@NotNull(message = "Required field")
	private Integer percentageValue;
}
