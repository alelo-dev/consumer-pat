package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;


    public List<Consumer> consumersList() {
        return consumerRepository.getAllConsumersList();
    }

    public Consumer updateConsumer(Integer id, Consumer consumer){
        Consumer existingConsumer = consumerRepository.getReferenceById(id);

        existingConsumer.setName(consumer.getName());
        existingConsumer.setDocumentNumber(consumer.getDocumentNumber());
        existingConsumer.setBirthDate(consumer.getBirthDate());
        existingConsumer.setStreet(consumer.getStreet());
        existingConsumer.setNumber(consumer.getNumber());
        existingConsumer.setCity(consumer.getCity());
        existingConsumer.setCountry(consumer.getCountry());
        existingConsumer.setPortalCode(consumer.getPortalCode());
        existingConsumer.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
        existingConsumer.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
        existingConsumer.setPhoneNumber(consumer.getPhoneNumber());
        existingConsumer.setEmail(consumer.getEmail());

        return consumerRepository.save(existingConsumer);
    }
}
