package br.com.alelo.consumer.consumerpat.application.card.buy.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {
	private Long id;
	private String establishmentName;
	private String productDescription;
	private LocalDateTime dateBuy;
	private Long cardNumber;
	private BigDecimal value;
}
