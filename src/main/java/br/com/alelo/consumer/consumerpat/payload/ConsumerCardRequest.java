package br.com.alelo.consumer.consumerpat.payload;

import br.com.alelo.consumer.consumerpat.enumeration.CardType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConsumerCardRequest{

	private String cardNumber;
	private CardType cardType;

}