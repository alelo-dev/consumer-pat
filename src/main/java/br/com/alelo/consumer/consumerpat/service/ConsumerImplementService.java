package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dao.ConsumerDAO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerImplementService implements ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    private ConsumerDAO consumerDao;

    public List<Consumer> getAllConsumersList() {
        return consumerDao.listAllConsumersDao();
    }

    public void createConsumer(Consumer consumer) {
        consumerDao.createConsumeDao(consumer);
    }

    public void updateConsumer(Consumer consumer) {
        consumerDao.updateConsumerDao(consumer);
    }

}