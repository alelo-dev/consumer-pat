package br.com.alelo.consumer.consumerpat.domain.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

	private String number;
	private CardType type;
	private BigDecimal balance;
	
}
