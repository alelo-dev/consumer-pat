package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;

public class ContactsCreationDto extends ContactsBaseDto{

    @Builder
    ContactsCreationDto(int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email) {
        super(mobilePhoneNumber, residencePhoneNumber, phoneNumber, email);
    }
}
