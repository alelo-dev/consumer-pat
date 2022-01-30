package br.com.alelo.consumer.consumerpat.service;

import static java.util.Objects.isNull;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.exception.NoSuchElementFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Transactional
    public Consumer save(ConsumerDTO consumerDTO) {

        Consumer consumer = ConsumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return repository.save(consumer);
    }

    @Transactional
    public Consumer update(ConsumerDTO consumerDTO) {

        if(isNull(consumerDTO.getId())){
            throw new NoSuchElementFoundException("ID Consumer not Found");
        }
        Consumer consumer = ConsumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return repository.save(consumer);
    }


    public Page<Consumer> getAllConsumersList( Pageable pageable) {
        return repository.findAll(pageable);
    }

}
