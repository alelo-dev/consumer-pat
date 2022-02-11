package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.EstablishmentType;
import lombok.Data;

@Data
public class CardDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String number;
	private double balance;
	private EstablishmentType type;
	
	public Card toCard() {
		Card card = new Card();
		card.setId(this.getId());
		card.setNumber(this.getNumber());
		card.setBalance(this.getBalance());
		card.setType(this.getType());
		return card;
	}
	
	public static CardDto fromCard(Card card) {
		if (card != null) {
			CardDto dto = new CardDto();
			dto.setId(card.getId());
			dto.setNumber(card.getNumber());
			dto.setBalance(card.getBalance());
			dto.setType(card.getType());
			return dto;
		}
		return null;
	}
	
	
}
