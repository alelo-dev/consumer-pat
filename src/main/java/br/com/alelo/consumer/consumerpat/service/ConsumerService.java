package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;

@Service
public class ConsumerService {

	@Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;
    
    public Page<Consumer> getAllConsumers(Pageable pageable) {
    	return consumerRepository.findAll(pageable);
    }
    
    public Consumer createConsumer(Consumer consumer) {
    	return consumerRepository.save(consumer);
    }
    
    public Consumer updateConsumer(Consumer consumer) {
    	Consumer persistentConsumer = consumerRepository.findById(consumer.getId()).orElse(null);
    	if (persistentConsumer != null) {
    		consumer.setDrugstoreCardBalance(persistentConsumer.getDrugstoreCardBalance());
    		consumer.setFoodCardBalance(persistentConsumer.getFoodCardBalance());
    		consumer.setFuelCardBalance(persistentConsumer.getFuelCardBalance());
    		
    		return consumerRepository.save(consumer);
    	} else {
    		return null;
    	}
    }
    
    public boolean setBalance(int cardNumber, double value) {
    	return addDrugstoreCardBalance(cardNumber, value) ||
    			addFoodCardBalance(cardNumber, value) ||
    			addFuelCardBalance(cardNumber, value);
    }
    
    public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

    	boolean buy = false;
        if (establishmentType == Constants.ESTABLISHMENT_TYPE_FOOD) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            calculateExtraValue(value, -10);

            buy = addFoodCardBalance(cardNumber, -value);

        } else if(establishmentType == Constants.ESTABLISHMENT_TYPE_DRUGSTORE) {
        	buy = addDrugstoreCardBalance(cardNumber, -value);
        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        	calculateExtraValue(value, 35);

        	buy = addFuelCardBalance(cardNumber, -value);
        }

        if (buy) {
	        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
	        return extractRepository.save(extract);
        } else {
        	return null;
        }
    }
    
    private double calculateExtraValue(double value, int percentage) {
    	Double extra = (value / 100) * percentage;
        return value + extra;
    }
    
    private boolean addDrugstoreCardBalance(int cardNumber, double value) {
    	Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
    	if (consumer != null) {
    		double newBalance = consumer.getDrugstoreCardBalance() + value;
    		if (newBalance < 0) {
    			return false;
    		} else {
		    	consumer.setDrugstoreCardBalance(newBalance);
		        consumerRepository.save(consumer);
		        return true;
    		}
    	} else {
    		return false;
    	}
    }
    
    private boolean addFoodCardBalance(int cardNumber, double value) {
    	Consumer consumer = consumerRepository.findByFoodCardNumber(cardNumber);
    	if (consumer != null) {
    		double newBalance = consumer.getFoodCardBalance() + value;
    		if (newBalance < 0) {
    			return false;
    		} else {
		    	consumer.setFoodCardBalance(newBalance);
		        consumerRepository.save(consumer);
		        return true;
    		}
    	} else {
    		return false;
    	}
    }
    
    private boolean addFuelCardBalance(int cardNumber, double value) {
    	Consumer consumer = consumerRepository.findByFuelCardNumber(cardNumber);
    	if (consumer != null) {
    		double newBalance = consumer.getFuelCardBalance() + value;
    		if (newBalance < 0) {
    			return false;
    		} else {
    			consumer.setFuelCardBalance(newBalance);
		        consumerRepository.save(consumer);
		        return true;
	        }
		} else {
			return false;
		}
    }
}
