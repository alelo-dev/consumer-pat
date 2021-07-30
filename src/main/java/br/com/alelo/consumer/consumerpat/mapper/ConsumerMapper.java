package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.util.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {

    public ConsumerDTO getConsumerDTOFrom(Consumer entity) {
        ConsumerDTO result = ObjectUtils.copyTo(entity, ConsumerDTO.class);
        result.setCards(ObjectUtils.copyListTo(entity.getCards(), CardDTO.class));
        return result;
    }

    public ConsumerIdDTO getConsumerIdDTOFrom(Consumer entity) {
        return ObjectUtils.copyTo(entity, ConsumerIdDTO.class);
    }

    public Consumer getConsumerFrom(CreateConsumerDTO dto) {
        final Consumer result = ObjectUtils.copyTo(dto, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), Card.class));
        return result;
    }

    public Consumer getConsumerFrom(UpdateConsumerDTO dto) {
        final Consumer result = ObjectUtils.copyTo(dto, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), Card.class));
        return result;
    }
}
