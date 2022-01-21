package br.com.alelo.consumer.consumerpat.controller.validator;

import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;

import java.util.Objects;

public class ConsumerValidator {

    public static String validate(final CreateConsumerDTO createConsumerDTO) {

        if (Objects.isNull(createConsumerDTO.getDocumentNumber())) {
            return "documentNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getDrugstoreCardBalance())) {
            return "drugstoreCardBalance não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getDrugstoreNumber())) {
            return "drugstoreNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getFoodCardBalance())) {
            return "foodCardBalance não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getFoodCardNumber())) {
            return "foodCardNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getFuelCardBalance())) {
            return "fuelCardBalance não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getFuelCardNumber())) {
            return "fuelCardNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getMobilePhoneNumber())) {
            return "mobilePhoneNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getNumber())) {
            return "number não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getPhoneNumber())) {
            return "phoneNumber não pode ser null";
        }
        if (Objects.isNull(createConsumerDTO.getResidencePhoneNumber())) {
            return "residencePhoneNumber não pode ser null";
        }
        return null;
    }

}
