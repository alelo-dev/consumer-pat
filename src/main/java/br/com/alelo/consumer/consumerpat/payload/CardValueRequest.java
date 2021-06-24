package br.com.alelo.consumer.consumerpat.payload;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardValueRequest {
	
	private BigDecimal value;
	
}