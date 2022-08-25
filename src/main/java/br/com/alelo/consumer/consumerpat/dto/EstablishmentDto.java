package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EstablishmentDto {

	private String name;
	
	private TypeCard type;

}
