package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private Long id;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;
}
