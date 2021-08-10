package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.CardUpdateBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsumerMapper {
    ConsumerMapper INSTANCE = Mappers.getMapper(ConsumerMapper.class);

    Consumer dtoToConsumer(ConsumerDTO consumerDTO);
    CardUpdateBalanceDTO cardEntityToDto(Card card);
}
