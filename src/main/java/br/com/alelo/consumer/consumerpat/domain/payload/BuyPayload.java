package br.com.alelo.consumer.consumerpat.domain.payload;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import lombok.Data;

@Data
public class BuyPayload {
	
	private CardType establishmentType;
	private String establishmentName;
	private String productDescription;
	private BigDecimal value;

}
