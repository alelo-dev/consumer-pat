package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;

@Getter
public class AddressRequest {
    private String idAdress;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;
}
