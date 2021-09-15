package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.response.AddressDto;
import br.com.alelo.consumer.consumerpat.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .number(addressDto.getNumber())
                .postalCode(addressDto.getPostalCode())
                .street(addressDto.getStreet())
                .build();
    }

    public AddressDto toDto(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .number(address.getNumber())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }
}
