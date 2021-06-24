package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreationDto extends AddressBaseDto {

    @Builder
    AddressCreationDto(String street, int number, String city, String country, int portalCode) {
        super(street, number, city, country, portalCode);
    }
}
