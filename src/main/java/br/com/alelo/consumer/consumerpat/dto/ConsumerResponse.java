package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ConsumerResponse {

    private Integer id;
    private String name;
    private String documentNumber;
    private Date birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;

    private Set<CardResponse> cards;

}
