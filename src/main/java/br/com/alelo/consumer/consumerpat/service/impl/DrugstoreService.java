package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alelo.consumer.consumerpat.service.ITransactionBuy;

public class DrugstoreService implements ITransactionBuy{
	
    @Autowired
    private CardService cardService;

	@Override
	public void buy(Long cardNumber, BigDecimal value) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
