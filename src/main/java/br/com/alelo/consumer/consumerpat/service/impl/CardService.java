package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class CardService {

	public void creditBalance(Long cardNumber, BigDecimal value) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
//	@Override
//	public Consumer setBalance(int cardNumber, double value) {
//		Consumer consumer = null;
//		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
//
//		if (consumer != null) {
//			// é cartão de farmácia
//			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
//			
//		} else {
//			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
//			if (consumer != null) {
//				// é cartão de refeição
//	
//			} else {
//				// É cartão de combustivel
//				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
//				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
//
//			}
//		}
//		
//		return consumerRepository.save(consumer);
//
//	}
	
	

}
