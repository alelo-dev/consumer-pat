package br.com.alelo.consumer.consumerpat.model.dto;

import lombok.Builder;
import lombok.Getter;

import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;

@Builder
@Getter
public class ConsumerCardDTO{

	private String cardNumber;
	private Double cardBalance;
	private CardType cardType;

}