package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

/**
 * 
 * @author Alexandre Ventecinco
 * @since  15/09/2021
 * 
 * @version 1.0
 * 
 * Classe de serviço para operações básicas de CRUD do Consumer
 *
 */
@Service
public class ConsumerService {
	
	@Autowired
	private ConsumerRepository repository ;
	
    public List<Consumer> getAllConsumersList() {
    	return this.repository.getAllConsumersList() ;    	
    }

    public Consumer findByFoodCardNumber(int cardNumber) {
    	return this.repository.findByFoodCardNumber(cardNumber) ;
    }

    public Consumer findByFuelCardNumber(int cardNumber) {
    	return this.repository.findByFuelCardNumber(cardNumber) ;
    	
    }

    public Consumer findByDrugstoreNumber(int cardNumber) {
    	return this.repository.findByDrugstoreNumber(cardNumber) ;
    	
    }

    @Transactional
    public void save( Consumer consumer ) {
    	this.repository.save( consumer ) ;
    }

}
