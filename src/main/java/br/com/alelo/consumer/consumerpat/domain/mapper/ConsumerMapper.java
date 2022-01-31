package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ConsumerMapper{

    public Consumer dtoToEntity(ConsumerDTO dto){
        var consumer = new Consumer();
        BeanUtils.copyProperties(dto, consumer);
        return consumer;
    }


    public ConsumerDTO entityToDTO(Consumer consumer){
        var dto = new ConsumerDTO();
        BeanUtils.copyProperties(consumer, dto);
        return dto;
    }
}
