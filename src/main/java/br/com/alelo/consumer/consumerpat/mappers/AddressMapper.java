package br.com.alelo.consumer.consumerpat.mappers;

import br.com.alelo.consumer.consumerpat.domain.model.Address;
import br.com.alelo.consumer.consumerpat.dto.AddressCreationDto;
import br.com.alelo.consumer.consumerpat.dto.AddressResponseDto;
import br.com.alelo.consumer.consumerpat.dto.AddressUpdateDto;

public class AddressMapper {

    public static AddressResponseDto mapAddressToAddressCreationDto(Address address){
        return AddressResponseDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .country(address.getCountry())
                .number(address.getNumber())
                .portalCode(address.getPortalCode())
                .street(address.getStreet())
                .build();
    }

    public static Address mapAddressCreationDtoToAddress(AddressCreationDto addressDto){
        Address address = new Address();
                address.setCity(addressDto.getCity());
                address.setCountry(addressDto.getCountry());
                address.setNumber(addressDto.getNumber());
                address.setPortalCode(addressDto.getPortalCode());
                address.setStreet(addressDto.getStreet());

        return address;
    }

    public static Address mapAddressUpdateDtoToAddress(AddressUpdateDto addressDto, Address address){
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setNumber(addressDto.getNumber());
        address.setPortalCode(addressDto.getPortalCode());
        address.setStreet(addressDto.getStreet());

        return address;
    }
}
