package br.com.alelo.consumer.consumerpat.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDTO {

private Long id;
	
	private BigDecimal amount;
	private String cardNumber;

}
