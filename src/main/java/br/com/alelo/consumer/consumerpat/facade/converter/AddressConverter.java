package br.com.alelo.consumer.consumerpat.facade.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.controller.dto.AddressDto;
import br.com.alelo.consumer.consumerpat.controller.dto.AddressSaveDto;
import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressConverter {
    
    public static Collection<AddressDto> toDtoList(final Collection<Address> addresses) {
        
        if (addresses == null) {
            return null;
        }
        
        return addresses.stream().map(address -> {
            return AddressDto.builder().id(address.getId()).street(address.getStreet()).city(address.getCity())
                    .consumerId(address.getConsumer().getId()).country(address.getCountry())
                    .postalCode(address.getPostalCode()).build();
        }).collect(Collectors.toList());
    }
    
    public static Collection<AddressDto> toDtoSaveList(final Collection<AddressSaveDto> addresses) {
        
        if (addresses == null) {
            return null;
        }
        
        return addresses.stream().map(address -> {
            return AddressDto.builder().city(address.getCity()).country(address.getCountry())
                    .number(address.getNumber()).postalCode(address.getPostalCode()).street(address.getStreet())
                    .build();
        }).collect(Collectors.toList());
    }
    
    public static Address toEntity(final AddressDto address) {

        if (address == null) {
            return null;
        }

        return Address.builder().city(address.getCity())
                .consumer(Consumer.builder().id(address.getConsumerId()).build()).country(address.getCountry())
                .number(address.getNumber()).street(address.getStreet()).postalCode(address.getPostalCode()).build();
    }
    
    public static Collection<Address> toList(final Collection<AddressDto> addresses) {
        if (addresses == null) {
            return null;
        }
        
        return addresses.stream()
                .map(address -> Address.builder().id(address.getId()).street(address.getStreet())
                        .number(address.getNumber()).city(address.getCity()).country(address.getCountry())
                        .consumer(Consumer.builder().id(address.getConsumerId()).build()).build())
                .collect(Collectors.toList());
    }
    
}
