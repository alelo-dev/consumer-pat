package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;

@Service
@Transactional
public class ConsumerService {
	
	@Autowired
    private ConsumerRepository consumerRepository;
	
	@Autowired
	private ExtractRepository extractRepository;
	
	public void createConsumer(Consumer consumer) {
		consumerRepository.save(consumer);
	}
	
	public void updateConsumer(@RequestBody Consumer consumer) {
		consumerRepository.save(consumer);
	}
	
	public List<Consumer> getAllConsumers() {
		return consumerRepository.findAll();
	}
	
	public void updateBalanceByCardNumber(int cardNumber, double newBalance) {
		Optional<Consumer> consumerFoundOpt = consumerRepository.findByCardNumber(cardNumber);
		
		if (consumerFoundOpt.isEmpty()) {
			// TODO not found exception
			return;
		}
		
		Consumer consumer = consumerFoundOpt.get();
		
		if (consumer.getDrugstoreCardNumber() == cardNumber) {
			consumerRepository.updateDrugstoreCardBalanceByCardNumber(cardNumber, newBalance);
		} else if (consumer.getFuelCardNumber() == cardNumber) {
			consumerRepository.updateFuelCardBalanceByCardNumber(cardNumber, newBalance);
		} else if (consumer.getFoodCardNumber() == cardNumber) {
			consumerRepository.updateFoodCardBalanceByCardNumber(cardNumber, newBalance);
		}
	}
	
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
		if (establishmentType == EstablishmentType.DRUGSTORE.getCode()) {
			Optional<Consumer> consumerOpt = consumerRepository.findByDrugstoreNumber(cardNumber);
			
			if (consumerOpt.isEmpty()) {
	        	return;
	        }
	        
	        Consumer consumer = consumerOpt.get();
			
            consumerRepository.updateDrugstoreCardBalanceByCardNumber(cardNumber, consumer.getDrugstoreCardBalance() - value);
            
            createExtract(establishmentName, productDescription, new Date(), cardNumber, value);
            return;
        } 
		
		if (EstablishmentType.FOOD.getCode() == establishmentType) {
            value = getTotalValue(EstablishmentType.FOOD, value);

            Optional<Consumer> consumerOpt = consumerRepository.findByFoodCardNumber(cardNumber);
            
            if (consumerOpt.isEmpty()) {
            	// TODO not found exception
	        	return;
	        }
	        
	        Consumer consumer = consumerOpt.get();
            
            consumerRepository.updateFoodCardBalanceByCardNumber(cardNumber, consumer.getFoodCardBalance() -  value);
            
            createExtract(establishmentName, productDescription, new Date(), cardNumber, value);
            return;
        } 
		
		if (EstablishmentType.FUEL.getCode() == establishmentType) {
			value =  getTotalValue(EstablishmentType.FUEL, value);

	        Optional<Consumer> consumerOpt = consumerRepository.findByFuelCardNumber(cardNumber);
	        
	        if (consumerOpt.isEmpty()) {
	        	// TODO not found exception
	        	return;
	        }
	        
	        Consumer consumer = consumerOpt.get();
	        
	        consumerRepository.updateFuelCardBalanceByCardNumber(cardNumber, consumer.getFuelCardBalance() - value);
	        
	        createExtract(establishmentName, productDescription, new Date(), cardNumber, value);
            return;
		}
	 }
	
	private void createExtract(String establishmentName, String productDescription, Date date, int cardNumber, double value) {
		Extract extract = new Extract(establishmentName, productDescription, date, cardNumber, value);
        extractRepository.save(extract);
	}
	
	private double getTotalValue(EstablishmentType establishmentType, double value) {
		switch (establishmentType) {
		case FOOD:
			Double cashback = (value / 100) * 10;
			return value - cashback;
		case FUEL:
			Double tax  = (value / 100) * 35;
			return value + tax;
		default:
			return value;
		}
	}

}
