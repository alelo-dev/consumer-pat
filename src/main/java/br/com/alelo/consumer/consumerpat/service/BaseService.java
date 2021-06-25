package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class BaseService {
	
	@Autowired
    protected ConsumerRepository consumerRepository;

	protected void addCardBalance(Consumer consumer, Card card, double value) throws BusinessException {
    	double newBalance = card.getBalance() + value;
		if (newBalance < 0) {
			throw new BusinessException("Insufficient balance.");
		} else {
			card.setBalance(newBalance);
	        consumerRepository.save(consumer);
		}
    }
    
    protected boolean addDrugstoreCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByDrugstoreCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getDrugstoreCard(), value);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    protected boolean addFoodCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByFoodCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getFoodCard(), value);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    protected boolean addFuelCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByFuelCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getFuelCard(), value);
    		return true;
		} else {
			return false;
		}
    }
}
