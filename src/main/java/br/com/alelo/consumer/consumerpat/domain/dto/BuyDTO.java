package br.com.alelo.consumer.consumerpat.domain.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import lombok.Data;

@Data
public class BuyDTO {
	
	private CardType establishmentType;
	private String establishmentName;
	private String productDescription;
	private BigDecimal value;

}
