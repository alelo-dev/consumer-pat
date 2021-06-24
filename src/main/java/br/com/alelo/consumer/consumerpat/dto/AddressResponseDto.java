package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto extends AddressBaseDto{

    private int id;

    @Builder
    public AddressResponseDto(String street, int number, String city, String country, int portalCode, int id) {
        super(street, number, city, country, portalCode);
        this.id = id;
    }
}
