package br.com.alelo.consumer.consumerpat.application.card.buy.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardBuyRequest {
	private Long cardNumber;
	private String establishmentName;
	private String productDescription;
	private BigDecimal value;
}
