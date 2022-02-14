package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ApplicationException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	public Page<Consumer> getAllConsumersListWhithPagination(Pageable pageable){
		return consumerRepository.findAll(pageable);
	}	
	
	public Consumer findByDrugstoreNumber(String cardNumber){
		Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
				
		if (consumer != null) {
			return consumer;
		}else {
        	throw new ApplicationException("Cartão não encontrado na base de dados.");
        }
	}
	

	public Consumer findByFoodCardNumber(String cardNumber){
		Consumer consumer = consumerRepository.findByFoodCardNumber(cardNumber);
		
		if (consumer != null) {
			return consumer;
		}else {
        	throw new ApplicationException("Cartão não encontrado na base de dados.");
        }
	}
	
	public Consumer findByFuelCardNumber(String cardNumber){
		Consumer consumer = consumerRepository.findByFuelCardNumber(cardNumber);
		
		if (consumer != null) {
			return consumer;
		}else {
        	throw new ApplicationException("Cartão não encontrado na base de dados.");
        }
	}	
	

	public Consumer save(Consumer consumer) {
		
		Consumer consumerBanco = consumerRepository.save(consumer);
		
//		if (consumer.getCards() != null) {
//		
//			consumer.getCards().forEach( 
//					cardAux -> {
//						cardAux.setConsumer(consumerBanco);
//						cardRepository.save(cardAux);						
//					});
//		}		
		
		
		return consumerBanco;
	}
	
	public Consumer updateConsumer(Integer id, Consumer consumer) {

		Consumer lConsumer = findById(id);		

		BeanUtils.copyProperties(consumer, lConsumer, "id", "foodCardBalance", "fuelCardBalance", "drugstoreCardBalance");
		
		lConsumer = consumerRepository.save(lConsumer);
		
		return lConsumer;
		
	}
	
	public Consumer findById(Integer codigo) {
		
		Optional<Consumer> optional = consumerRepository.findById(codigo);
		
		return optional.orElseThrow(() ->
			new EmptyResultDataAccessException(1)
		);
	}
	
	
	public Consumer setBalance(Card card) {
		Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(card.getNumber());

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + card.getBalance());
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(card.getNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + card.getBalance());
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(card.getNumber());
                if (consumer!= null) {
                	consumer.setFuelCardBalance(consumer.getFuelCardBalance() + card.getBalance());
                	consumerRepository.save(consumer);
                }
                else {
                	throw new ApplicationException("Cartão não encontrado na base de dados.");
                }
            }
        }
        
        return consumer;
	}
}
