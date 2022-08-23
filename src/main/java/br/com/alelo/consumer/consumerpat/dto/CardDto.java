package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import lombok.Data;

@Data
public class CardDto {

	private TypeCard type;
	
	private Long number;
	
}
