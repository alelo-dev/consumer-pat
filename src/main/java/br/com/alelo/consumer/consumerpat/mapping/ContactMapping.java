package br.com.alelo.consumer.consumerpat.mapping;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Contact;

public class ContactMapping {

    private ContactMapping(){}

    public static Contact to(ConsumerDTO dto) {
        return Contact
                .builder()
                .email(dto.getEmail())
                .mobilePhoneNumber(dto.getMobilePhoneNumber())
                .phoneNumber(dto.getPhoneNumber())
                .residencePhoneNumber(dto.getResidencePhoneNumber())
                .build();
    }

}
