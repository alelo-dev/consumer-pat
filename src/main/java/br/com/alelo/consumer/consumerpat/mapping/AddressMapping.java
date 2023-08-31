package br.com.alelo.consumer.consumerpat.mapping;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;

public class AddressMapping {

    private AddressMapping(){}

    public static Address to(ConsumerDTO dto) {
        return Address
                .builder()
                .city(dto.getCity())
                .country(dto.getCountry())
                .number(dto.getNumber())
                .postalCode(dto.getPostalCode())
                .street(dto.getStreet())
                .build();
    }

}
