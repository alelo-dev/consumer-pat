package br.com.alelo.consumer.consumerpat.entity.mapper;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import org.springframework.beans.BeanUtils;

public class ConsumerMapper {

    public static Consumer dtoToEntity(ConsumerDTO dto){
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(dto, consumer);
        return consumer;
    }


    public static ConsumerDTO entityToDTO(Consumer consumer){
        ConsumerDTO dto = new ConsumerDTO();
        BeanUtils.copyProperties(dto, consumer);
        return dto;
    }

}
