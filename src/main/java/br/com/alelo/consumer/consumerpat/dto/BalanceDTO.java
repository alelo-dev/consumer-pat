package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BalanceDTO implements Serializable{

	
	public Long clientId;
	
	public Long cardNumber;
	
	public Double balance;
	
}
