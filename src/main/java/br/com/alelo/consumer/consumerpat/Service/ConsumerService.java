package br.com.alelo.consumer.consumerpat.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    /* Listagem dos Cliente */
    public List<Consumer> listConsumerService(){
        return consumerRepository.getAllConsumersList();
    }

    /* Cadastra novo Cliente */
    public Consumer createConsumerService(Consumer consumer){
        try {
            return consumerRepository.save(consumer);
        } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
