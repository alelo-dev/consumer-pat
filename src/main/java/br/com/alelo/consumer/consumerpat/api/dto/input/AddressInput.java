package br.com.alelo.consumer.consumerpat.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {
    
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;
}
