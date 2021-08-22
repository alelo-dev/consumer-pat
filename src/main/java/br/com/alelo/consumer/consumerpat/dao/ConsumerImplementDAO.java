package br.com.alelo.consumer.consumerpat.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Component
public class ConsumerImplementDAO implements ConsumerDAO {

	@Autowired
    ConsumerRepository repository;
	
	@Autowired
    ExtractRepository extractRepository;
	
	/* Deve listar todos os clientes (cerca de 500) */
	@Transactional(readOnly = true)
	public List<Consumer> listAllConsumersDao() {
    	return repository.getAllConsumersList();
    }
	
	/* Cadastrar novos clientes */
	@Transactional
	public void createConsumeDao(Consumer consumer) {
    	repository.save(consumer);
    }
	
	// Não deve ser possível alterar o saldo do cartão
	@Transactional
	public void updateConsumerDao(ConsumerDTO consumerDTO) {
		
		Optional<Consumer> consumer = repository.findById(consumerDTO.getId());
    	
    	if(consumer.isPresent()) {
 
	    	consumer.get().setName(consumerDTO.getName());
	    	consumer.get().setDocumentNumber(consumerDTO.getDocumentNumber());
	    	consumer.get().setBirthDate(consumerDTO.getBirthDate());
	    	consumer.get().setMobilePhoneNumber(consumerDTO.getMobilePhoneNumber());
	    	consumer.get().setResidencePhoneNumber(consumerDTO.getResidencePhoneNumber());
	    	consumer.get().setPhoneNumber(consumerDTO.getPhoneNumber());
	    	consumer.get().setEmail(consumerDTO.getEmail());
	    	consumer.get().setStreet(consumerDTO.getStreet());
	    	consumer.get().setNumber(consumerDTO.getNumber());
	    	consumer.get().setCity(consumerDTO.getCity());
	    	consumer.get().setCountry(consumerDTO.getCountry());
	    	consumer.get().setPortalCode(consumerDTO.getPortalCode());
	    	consumer.get().setBirthDate(consumerDTO.getBirthDate());
	    	consumer.get().setFoodCardNumber(consumerDTO.getFoodCardNumber());
	    	consumer.get().setFuelCardNumber(consumerDTO.getFuelCardNumber());
	    	consumer.get().setDrugstoreNumber(consumerDTO.getDrugstoreNumber());
	    	
	    	repository.save(consumer.get());
    	} else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado!");
    	}
	}
	
	/*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
	@Transactional
	public void setBalanceDao(ConsumerCardDTO consumerCardDTO) {
		
		Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(consumerCardDTO.getCardNumber());

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + consumerCardDTO.getValue());
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(consumerCardDTO.getCardNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + consumerCardDTO.getValue());
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(consumerCardDTO.getCardNumber());
                if(consumer != null) {
                	consumer.setFuelCardBalance(consumer.getFuelCardBalance() + consumerCardDTO.getValue());
                	repository.save(consumer);
                } else {
            		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informe um número de cartão válido!");
            	}
            }
        }
	}
		
	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
	@Transactional
	public void buyDao(ConsumerCardDTO consumerCardDTO) {
		
		Consumer consumer = null;
        double value = consumerCardDTO.getValue();
        
        try {
	        switch(consumerCardDTO.getEstablishmentType()) {
	        	case 1:
	        		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	                Double cashback  = (value / 100) * 10;
	                value = value - cashback;
	                consumer = repository.findByFoodCardNumber(consumerCardDTO.getCardNumber());
	                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
	                repository.save(consumer);
	                break;
	        	case 2:
	        		consumer = repository.findByDrugstoreNumber(consumerCardDTO.getCardNumber());
	                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
	                repository.save(consumer);
	                break;
	            default:
	            	// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	                Double tax  = (value / 100) * 35;
	                value = value + tax;
	                consumer = repository.findByFuelCardNumber(consumerCardDTO.getCardNumber());
	                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
	                repository.save(consumer);
	        }	
	        
	        Extract extract = new Extract(consumerCardDTO.getEstablishmentId(), consumerCardDTO.getEstablishmentName(), 
	        	consumerCardDTO.getProductDescription(), new Date(), consumerCardDTO.getCardNumber(), consumerCardDTO.getValue());
	        
	        extractRepository.save(extract);
        } catch(NullPointerException ex) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informe um número de cartão válido!");
        }
	}
}
