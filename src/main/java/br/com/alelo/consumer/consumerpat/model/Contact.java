package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Contact {

    private int mobilePhoneNumber;
    
    private int residencePhoneNumber;
    
    private int phoneNumber;
    
    private String email;

}
