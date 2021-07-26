package br.com.alelo.consumer.consumerpat.facade;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumersDto;
import br.com.alelo.consumer.consumerpat.facade.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsumerFacade {
    
    private final ConsumerService service;
    
    public ConsumersDto findAll(final Pageable pageable) {
        var consumers = service.findAll(pageable);
        return new ConsumersDto(consumers.getTotalElements(), pageable.getPageSize(), consumers.getNumber(),
                ConsumerConverter.toDtoList(consumers.getContent()));
    }
    
    public ConsumerDto save(final ConsumerSaveDto consumer) {
        var c = service.save(ConsumerConverter.toEntity(consumer));
        return ConsumerConverter.toDto(c);
    }
    
    @Transactional
    public void update(final ConsumerUpdateDto consumer) {
        service.update(ConsumerConverter.toEntity(consumer));
    }
    
}
