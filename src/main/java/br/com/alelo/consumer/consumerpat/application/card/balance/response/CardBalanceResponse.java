package br.com.alelo.consumer.consumerpat.application.card.balance.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardBalanceResponse {
	private Long cardId;
	private BigDecimal balance;

}
