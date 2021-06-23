package br.com.alelo.consumer.consumerpat.payload;

import br.com.alelo.consumer.consumerpat.enumeration.CardType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConsumerCardResponse{

	private String cardNumber;
	private CardType cardType;
	private Double cardBalance;

}