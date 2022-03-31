package br.com.alelo.consumer.consumerpat.parser.impls;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.parser.interfaces.ConsumerParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVAÇÃO: Nestes casos de fazer parse de objetos DTOs para entidades e vice-versa, eu utilizaria o
 *
 * @Mapper de org.mapstruct.Mapper, porém, como requisito deste projeto de teste pede para que não
 * inclua qualquer lib, precisei fazer desta forma implementada abaixo.
 */
@Component
public class ConsumerParserImpl implements ConsumerParser {
    @Override
    public Consumer parse(ConsumerDTO consumerDTO) {
        Consumer consumer = Consumer.builder()
                .id(consumerDTO.getId())
                .name(consumerDTO.getName())
                .documentNumber(consumerDTO.getDocumentNumber())
                .birthDate(consumerDTO.getBirthDate())
                .contact(Contact.builder()
                        .id(consumerDTO.getContact().getId())
                        .mobilePhoneNumber(consumerDTO.getContact().getMobilePhoneNumber())
                        .residencePhoneNumber(consumerDTO.getContact().getResidencePhoneNumber())
                        .phoneNumber(consumerDTO.getContact().getPhoneNumber())
                        .email(consumerDTO.getContact().getEmail())
                        .build())
                .address(Address.builder()
                        .id(consumerDTO.getAddress().getId())
                        .street(consumerDTO.getAddress().getStreet())
                        .number(consumerDTO.getAddress().getNumber())
                        .city(consumerDTO.getAddress().getCity())
                        .country(consumerDTO.getAddress().getCountry())
                        .portalCode(consumerDTO.getAddress().getPortalCode())
                        .build())
                .build();

        consumer.setCards(getCardList(consumerDTO.getCards(), consumer));
        consumer.getContact().setConsumer(consumer);
        consumer.getAddress().setConsumer(consumer);

        return consumer;
    }

    @Override
    public List<ConsumerDTO> parse(List<Consumer> consumerList) {
        List<ConsumerDTO> consumerDTOList = new ArrayList<>();

        consumerList.stream().forEach(c -> consumerDTOList.add(parse(c)));

        return consumerDTOList;
    }

    private ConsumerDTO parse(Consumer consumer) {
        return ConsumerDTO.builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .contact(ContactDTO.builder()
                        .id(consumer.getContact().getId())
                        .mobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())
                        .residencePhoneNumber(consumer.getContact().getResidencePhoneNumber())
                        .phoneNumber(consumer.getContact().getPhoneNumber())
                        .email(consumer.getContact().getEmail())
                        .build())
                .address(AddressDTO.builder()
                        .id(consumer.getAddress().getId())
                        .street(consumer.getAddress().getStreet())
                        .number(consumer.getAddress().getNumber())
                        .city(consumer.getAddress().getCity())
                        .country(consumer.getAddress().getCountry())
                        .portalCode(consumer.getAddress().getPortalCode())
                        .build())
                .cards(getCardDTOList(consumer.getCards()))
                .build();
    }

    private List<Card> getCardList(List<CardDTO> cardDTOList, Consumer consumer) {
        List<Card> cardList = new ArrayList<>();

        cardDTOList.stream().forEach(c ->
                cardList.add(
                        Card.builder()
                                .number(c.getNumber())
                                .balance(c.getBalance())
                                .type(Type.builder()
                                        .id(c.getType().getId())
                                        .build())
                                .consumer(consumer)
                                .build()
                )
        );

        return cardList;
    }

    private List<CardDTO> getCardDTOList(List<Card> cardList) {
        List<CardDTO> cardDTOList = new ArrayList<>();

        cardList.stream().forEach(c ->
                cardDTOList.add(
                        CardDTO.builder()
                                .number(c.getNumber())
                                .balance(c.getBalance())
                                .type(TypeDTO.builder()
                                        .id(c.getType().getId())
                                        .build())
                                .build()
                )
        );

        return cardDTOList;
    }
}
