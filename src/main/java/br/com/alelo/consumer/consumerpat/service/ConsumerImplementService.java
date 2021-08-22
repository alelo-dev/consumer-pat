package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dao.ConsumerDAO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerImplementService implements ConsumerService {

	@Autowired
    ConsumerRepository repository;
    
    @Autowired
    private ConsumerDAO consumerDAO;
    
    public List<Consumer> listAllConsumersService() {
    	return consumerDAO.listAllConsumersDao();
    }
    
    public void createConsumeService(Consumer consumer) {
    	consumerDAO.createConsumeDao(consumer);
    }
    
    public void updateConsumerService(ConsumerDTO consumerDTO) {    	
    	consumerDAO.updateConsumerDao(consumerDTO);
    }
    
    public void setBalanceService(ConsumerCardDTO consumerCardDTO) {
    	consumerDAO.setBalanceDao(consumerCardDTO);
    }
    
    public void buyService(ConsumerCardDTO consumerCardDTO) {
    	
    	consumerDAO.buyDao(consumerCardDTO);
    }
}
