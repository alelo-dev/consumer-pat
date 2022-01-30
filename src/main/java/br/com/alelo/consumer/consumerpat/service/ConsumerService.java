package br.com.alelo.consumer.consumerpat.service;

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

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Transactional
    public ConsumerDTO save(ConsumerDTO consumerDTO) {

        var consumer = ConsumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return ConsumerMapper.entityToDTO(repository.save(consumer));
    }

    @Transactional
    public Consumer update(ConsumerDTO consumerDTO) {

        if(isNull(consumerDTO.getId())){
            throw new NoSuchElementFoundException("ID Consumer not Found");
        }
        var consumer = ConsumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return repository.save(consumer);
    }

    public Page<Consumer> getAllConsumersList( Pageable pageable) {
        return repository.findAll(pageable);
    }

}
