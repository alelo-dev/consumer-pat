package br.com.alelo.consumer.consumerpat.payload;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enumeration.CardType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardRequest {
	
	private CardType establishmentType;
	private String establishmentName; 
	private String productDescription;
	private BigDecimal value;
	
}