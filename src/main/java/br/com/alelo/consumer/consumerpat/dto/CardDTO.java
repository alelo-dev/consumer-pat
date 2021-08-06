package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
	
	private Long id;
	private Long number;
	private CardTypeEnum type;
	private Double balance;
	
	public static CardDTO to(Card card) {
		return new CardDTO(card.getId(), card.getNumber(), card.getType(), card.getBalance());
	}
	
	public Card toEntity() {
		return new Card(id, number, type, balance, null);
	}
}
