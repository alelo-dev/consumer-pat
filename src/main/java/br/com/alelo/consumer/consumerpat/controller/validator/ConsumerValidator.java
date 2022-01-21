package br.com.alelo.consumer.consumerpat.controller.validator;

import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;

import java.util.Objects;

public class ConsumerValidator {

    public static String validate(final CreateConsumerDTO createConsumerDTO) {

        if (Objects.isNull(createConsumerDTO.getDocumentNumber())) {
            return "documentNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getCreateCardDTOS())) {
            return "createPhoneDTO não pode ser null";
        }
        if (createConsumerDTO.getCreateCardDTOS().stream().anyMatch(createCardDTO -> Objects.isNull(createCardDTO.getCardType()))) {
            return "cardType não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getCreatePhoneDTOS())) {
            return "createPhoneDTO não pode ser null";
        }
        if (createConsumerDTO.getCreatePhoneDTOS().stream().anyMatch(createCardDTO -> Objects.isNull(createCardDTO.getPhoneType()))) {
            return "phoneType não pode ser null";
        }
        return null;
    }

}
