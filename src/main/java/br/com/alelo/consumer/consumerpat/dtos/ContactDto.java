package br.com.alelo.consumer.consumerpat.dtos;

import lombok.Data;

@Data
public class ContactDto {

    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;
}
