package br.com.alelo.consumer.consumerpat.controller.converter;

import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class ConsumerConverter {

    public static Consumer toEntity(final CreateConsumerDTO createConsumerDTO) {
        return Consumer.builder()
                .name(createConsumerDTO.getName())
                .documentNumber(createConsumerDTO.getDocumentNumber())
                .birthDate(createConsumerDTO.getBirthDate())
                .mobilePhoneNumber(createConsumerDTO.getMobilePhoneNumber())
                .residencePhoneNumber(createConsumerDTO.getResidencePhoneNumber())
                .phoneNumber(createConsumerDTO.getPhoneNumber())
                .email(createConsumerDTO.getEmail())
                .street(createConsumerDTO.getStreet())
                .number(createConsumerDTO.getNumber())
                .city(createConsumerDTO.getCity())
                .country(createConsumerDTO.getCountry())
                .postalCode(createConsumerDTO.getPostalCode())
                .foodCardNumber(createConsumerDTO.getFoodCardNumber())
                .foodCardBalance(createConsumerDTO.getFoodCardBalance())
                .fuelCardNumber(createConsumerDTO.getFuelCardNumber())
                .fuelCardBalance(createConsumerDTO.getFuelCardBalance())
                .drugstoreNumber(createConsumerDTO.getDrugstoreNumber())
                .drugstoreCardBalance(createConsumerDTO.getDrugstoreCardBalance())
                .build();
    }


}
