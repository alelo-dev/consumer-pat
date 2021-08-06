package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import lombok.Data;

@Data
public class CardCreateDTO {

	private Long number;
	private CardTypeEnum type;
	private Double balance;
	
	public Card toEntity() {
		return new Card(null, number, type, balance, null);
	}
}
