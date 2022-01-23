package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardConsumer {
	
	private String number;
    private BigDecimal balance;
    private TypeCard cardType;

}
