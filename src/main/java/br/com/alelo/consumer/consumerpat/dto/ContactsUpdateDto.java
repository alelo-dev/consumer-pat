package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;

public class ContactsUpdateDto extends ContactsBaseDto{

    private Integer id;

    @Builder
    ContactsUpdateDto(int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email, Integer id) {
        super(mobilePhoneNumber, residencePhoneNumber, phoneNumber, email);
        this.id = id;
    }
}
