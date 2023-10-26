package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response;

import lombok.Data;

@Data
public class AddressResponse {
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;
}
