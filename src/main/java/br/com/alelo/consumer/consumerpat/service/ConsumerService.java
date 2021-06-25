package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;

@Service
public class ConsumerService extends BaseService {
    
    public Page<Consumer> getAllConsumers(Pageable pageable) {
    	return consumerRepository.findAll(pageable);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Consumer createConsumer(Consumer consumer) {
    	return consumerRepository.save(consumer);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Consumer updateConsumer(Consumer consumer) {
    	Consumer persistentConsumer = consumerRepository.findById(consumer.getId()).orElse(null);
    	if (persistentConsumer != null) {
    		consumer.getDrugstoreCard().setBalance(persistentConsumer.getDrugstoreCard().getBalance());
    		consumer.getFoodCard().setBalance(persistentConsumer.getFoodCard().getBalance());
    		consumer.getFuelCard().setBalance(persistentConsumer.getFuelCard().getBalance());
    		
    		return consumerRepository.save(consumer);
    	} else {
    		return null;
    	}
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addBalance(int cardNumber, double value) throws BusinessException {
    	boolean add = value > 0 && 
    		(addDrugstoreCardBalance(cardNumber, value) ||
    		 addFoodCardBalance(cardNumber, value) ||
    		 addFuelCardBalance(cardNumber, value));
    	if (!add) {
    		throw new BusinessException("Consumer not found.");
    	}
    }
}
