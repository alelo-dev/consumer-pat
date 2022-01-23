package br.com.alelo.consumer.consumerpat.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.GetConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Component
public class ConsumerMapper {

    @Autowired
    protected ModelMapper mapper;
    
    public Consumer createToEntity(CreateConsumerDTO dto) {
        return mapper.map(dto, Consumer.class);
    }

    public Consumer updateToEntity(UpdateConsumerDTO dto) {
        return mapper.map(dto, Consumer.class);
    }

    public GetConsumerDTO toDTO(Consumer consumer) {
        return mapper.map(consumer, GetConsumerDTO.class);
    }

    public List<GetConsumerDTO> toListDTO(List<Consumer> consumers) {
        return consumers.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
