package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMapper {

    private final CardMapper cardMapper;

    public ConsumerDTO consumerToConsumerDTO(final Consumer consumer){
        return ConsumerDTO.builder()
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .mobilePhoneNumber(consumer.getMobilePhoneNumber())
                .residencePhoneNumber(consumer.getResidencePhoneNumber())
                .phoneNumber(consumer.getPhoneNumber())
                .email(consumer.getEmail())
                .street(consumer.getStreet())
                .number(consumer.getNumber())
                .city(consumer.getCity())
                .country(consumer.getCountry())
                .portalCode(consumer.getPortalCode())
                .cards(cardMapper.cardToListCardDTO(consumer.getCards()))
                .build();
    }

    public Consumer consumerDTOToConsumer(final ConsumerDTO consumerDTO){
        return Consumer.builder()
                .name(consumerDTO.getName())
                .documentNumber(consumerDTO.getDocumentNumber())
                .birthDate(consumerDTO.getBirthDate())
                .mobilePhoneNumber(consumerDTO.getMobilePhoneNumber())
                .residencePhoneNumber(consumerDTO.getResidencePhoneNumber())
                .phoneNumber(consumerDTO.getPhoneNumber())
                .email(consumerDTO.getEmail())
                .street(consumerDTO.getStreet())
                .number(consumerDTO.getNumber())
                .city(consumerDTO.getCity())
                .country(consumerDTO.getCountry())
                .portalCode(consumerDTO.getPortalCode())
                .cards(cardMapper.cardDTOToListCard(consumerDTO.getCards()))
                .build();
    }

}
