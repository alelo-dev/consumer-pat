package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDTO implements Serializable {
    private Long id;
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;
}
