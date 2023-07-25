package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@ToString
@EqualsAndHashCode
public class Contact {
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    @Email(message = "Must be a valid email address")
    private String email;

    public Contact(String mobilePhoneNumber, String residencePhoneNumber, String phoneNumber, String email) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
