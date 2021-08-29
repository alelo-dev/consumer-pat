package br.com.alelo.consumer.consumerpat.domain.payload;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import lombok.Data;

@Data
public class CardPayload {
	
	private String number;
	private CardType type;

}
