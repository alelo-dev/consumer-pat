package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceDTO {

	private String cardNumber;
	private BigDecimal value;
}
