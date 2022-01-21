package br.com.alelo.consumer.consumerpat.controller.converter;

import br.com.alelo.consumer.consumerpat.controller.dto.in.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseCardDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponsePhoneDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Phone;
import br.com.alelo.consumer.consumerpat.enums.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.enums.PhoneTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumerConverter {

    public static Consumer toEntity(final CreateConsumerDTO createConsumerDTO) {
        List<Card> cardList = new ArrayList<>();
        List<Phone> phoneList = new ArrayList<>();
        createConsumerDTO.getCreateCardDTOS().forEach(
                createCardDTO -> {
                    cardList.add(Card.builder()
                            .id(null)
                            .balance(createCardDTO.getBalance())
                            .cardType(createCardDTO.getCardType().getValue())
                            .number(createCardDTO.getNumber())
                            .createdAt(LocalDateTime.now())
                            .build());
                }
        );
        createConsumerDTO.getCreatePhoneDTOS().forEach(
                createPhoneDTO -> {
                    phoneList.add(Phone.builder()
                            .id(null)
                            .phoneType(createPhoneDTO.getPhoneType().getValue())
                            .number(createPhoneDTO.getNumber())
                            .createdAt(LocalDateTime.now())
                            .build());
                }
        );

        return Consumer.builder()
                .id(null)
                .name(createConsumerDTO.getName())
                .documentNumber(createConsumerDTO.getDocumentNumber())
                .birthDate(createConsumerDTO.getBirthDate())
                .phoneList(phoneList)
                .email(createConsumerDTO.getEmail())
                .street(createConsumerDTO.getStreet())
                .number(createConsumerDTO.getNumber())
                .city(createConsumerDTO.getCity())
                .country(createConsumerDTO.getCountry())
                .postalCode(createConsumerDTO.getPostalCode())
                .cardList(cardList)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ResponseConsumerDTO fromEntity(final Consumer consumer) {

        List<ResponsePhoneDTO> responsePhoneDTOS = new ArrayList<>();
        List<ResponseCardDTO> responseCardDTOS = new ArrayList<>();

        consumer.getPhoneList().forEach(phone -> {
            responsePhoneDTOS.add(
                    ResponsePhoneDTO.builder()
                            .number(phone.getNumber())
                            .phoneType(PhoneTypeEnum.getEnum(phone.getPhoneType()))
                    .build());

        });

        consumer.getCardList().forEach(card -> {
            responseCardDTOS.add(
                    ResponseCardDTO.builder()
                            .number(card.getNumber())
                            .cardType(CardTypeEnum.getEnum(card.getCardType()))
                            .balance(card.getBalance())
                            .build());

        });

        return ResponseConsumerDTO.builder()
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .phoneDTOS(responsePhoneDTOS)
                .email(consumer.getEmail())
                .street(consumer.getStreet())
                .number(consumer.getNumber())
                .city(consumer.getCity())
                .country(consumer.getCountry())
                .postalCode(consumer.getPostalCode())
                .cardDTOS(responseCardDTOS)
                .build();
    }
}
