package br.com.alelo.consumer.consumerpat.dto;


import lombok.Getter;

@Getter
public class ContactRequest {
    private String idContact;
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;
}