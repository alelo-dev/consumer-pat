package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

@Data
public class AddressConsumer {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;
}
