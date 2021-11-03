package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Contact {
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;
}
