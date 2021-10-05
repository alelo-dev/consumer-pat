package br.com.alelo.consumer.consumerpat.domain.dto;

import java.math.BigDecimal;
import br.com.alelo.consumer.consumerpat.domain.entity.CardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

private Long id;
	
	private BigDecimal balance;
	private CardType type;

}
