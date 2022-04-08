package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {

    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;

    public static AddressDTO from(Address address) {
        var dto = new AddressDTO();
        dto.street = address.getStreet();
        dto.number = address.getNumber();
        dto.city = address.getCity();
        dto.country = address.getCountry();
        dto.postalCode = address.getPostalCode();
        return dto;
    }

}
