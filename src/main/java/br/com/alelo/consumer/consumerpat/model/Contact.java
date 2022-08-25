package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Contact {

    private Long mobilePhoneNumber;
    
    private Long residencePhoneNumber;
    
    private Long phoneNumber;
    
    private String email;

}
