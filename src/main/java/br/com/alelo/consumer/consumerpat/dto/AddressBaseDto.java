package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class AddressBaseDto {
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;
}
