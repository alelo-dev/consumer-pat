package br.com.alelo.consumer.consumerpat.payload;

import lombok.Builder;
import lombok.Getter;

import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;

@Builder
@Getter
public class ConsumerCardRequest{

	private String cardNumber;
	private CardType cardType;

}