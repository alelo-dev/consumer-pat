package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

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
    		consumer.getDrugstoreCard().setBalance(persistentConsumer.getDrugstoreCard().getBalance());
    		consumer.getFoodCard().setBalance(persistentConsumer.getFoodCard().getBalance());
    		consumer.getFuelCard().setBalance(persistentConsumer.getFuelCard().getBalance());
    		
    		return consumerRepository.save(consumer);
    	} else {
    		return null;
    	}
    }
    
    public void addBalance(int cardNumber, double value) throws BusinessException {
    	boolean add = value > 0 && 
    		(addDrugstoreCardBalance(cardNumber, value) ||
    		 addFoodCardBalance(cardNumber, value) ||
    		 addFuelCardBalance(cardNumber, value));
    	if (!add) {
    		throw new BusinessException("Consumer not found.");
    	}
    }
    
    public Extract buy(ConsumerBuyDTO consumerBuyDTO) throws BusinessException {
    	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
    	
    	EstablishmentTypeEnum establishmentType = consumerBuyDTO.getEstablishmentType();
    	String establishmentName = consumerBuyDTO.getEstablishmentName();
    	int cardNumber = consumerBuyDTO.getCardNumber();
    	String productDescription = consumerBuyDTO.getProductDescription();
    	double value = consumerBuyDTO.getValue();

    	boolean buy = false;
    	if (value >= 0) {
    		value = calculateExtraValue(value, establishmentType);
    		
	        if (establishmentType == EstablishmentTypeEnum.FOOD) {
	            buy = addFoodCardBalance(cardNumber, -value);
	        } else if (establishmentType == EstablishmentTypeEnum.DRUGSTORE) {
	        	buy = addDrugstoreCardBalance(cardNumber, -value);
	        } else if (establishmentType == EstablishmentTypeEnum.FUEL) {
	        	buy = addFuelCardBalance(cardNumber, -value);
	        }
        }

        if (buy) {
	        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
	        return extractRepository.save(extract);
        } else {
        	throw new BusinessException("Consumer not found.");
        }
    }
    
    private double calculateExtraValue(double value, EstablishmentTypeEnum establishmentType) {
    	Double extra = new BigDecimal(value / 100).multiply(new BigDecimal(establishmentType.getExtraValuePercentage())).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return value + extra;
    }
    
    private void addCardBalance(Consumer consumer, Card card, double value) throws BusinessException {
    	double newBalance = card.getBalance() + value;
		if (newBalance < 0) {
			throw new BusinessException("Insufficient balance.");
		} else {
			card.setBalance(newBalance);
	        consumerRepository.save(consumer);
		}
    }
    
    private boolean addDrugstoreCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByDrugstoreCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getDrugstoreCard(), value);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private boolean addFoodCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByFoodCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getFoodCard(), value);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private boolean addFuelCardBalance(int cardNumber, double value) throws BusinessException {
    	Consumer consumer = consumerRepository.findByFuelCardNumber(cardNumber);
    	if (consumer != null) {
    		addCardBalance(consumer, consumer.getFuelCard(), value);
    		return true;
		} else {
			return false;
		}
    }
}
