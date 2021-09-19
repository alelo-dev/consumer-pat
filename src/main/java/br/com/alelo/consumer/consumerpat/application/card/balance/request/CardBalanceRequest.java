package br.com.alelo.consumer.consumerpat.application.card.balance.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardBalanceRequest {
	private Long cardNumber;
	private BigDecimal value;

}
