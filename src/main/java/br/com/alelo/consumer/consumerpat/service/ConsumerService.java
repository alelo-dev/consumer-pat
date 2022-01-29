package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    public void save(ConsumerDTO consumerDTO) {
        Consumer consumer = null;
        BeanUtils.copyProperties(consumerDTO, new Consumer());
        repository.save(consumer);

    }


    public List<Consumer> getAllConsumersList(int size, int page) {
        return repository.getAllConsumersList();
    }


}
