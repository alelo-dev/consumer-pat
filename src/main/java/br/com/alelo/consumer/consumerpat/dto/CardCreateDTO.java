package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateDTO {

	private String number;
	private CardTypeEnum type;
	private BigDecimal balance;
	
	public Card toEntity() {
		return new Card(null, number, type, balance, null);
	}
}
