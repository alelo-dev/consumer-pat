package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.exception.NoSuchElementFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class ConsumerService {


    private ConsumerRepository repository;
    private ConsumerMapper consumerMapper;

    @Autowired
    public ConsumerService(ConsumerRepository repository, ConsumerMapper consumerMapper) {
        this.repository = repository;
        this.consumerMapper = consumerMapper;
    }

    @Transactional(readOnly = true)
    public ConsumerDTO getById(Integer id) {
        var consumer = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return consumerMapper.entityToDTO(consumer);
    }

    @Transactional
    public ConsumerDTO save(ConsumerDTO consumerDTO) {

        var consumer = consumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return consumerMapper.entityToDTO(repository.save(consumer));
    }

    @Transactional
    public ConsumerDTO update(ConsumerDTO consumerDTO) {

        if(isNull(consumerDTO.getId())){
            throw new NoSuchElementFoundException("ID Consumer not Found");
        }
        var consumer = consumerMapper.dtoToEntity(consumerDTO);
        consumer.setCreateDate(LocalDateTime.now());
        return consumerMapper.entityToDTO(repository.save(consumer));
    }

    @Transactional(readOnly = true)
    public Page<ConsumerDTO> getAllConsumersList( Pageable pageable) {
        return repository.findAll(pageable).map(this::convert);
    }

    public ConsumerDTO convert(Consumer consumer) {
        return consumerMapper.entityToDTO(consumer);
    }

}
