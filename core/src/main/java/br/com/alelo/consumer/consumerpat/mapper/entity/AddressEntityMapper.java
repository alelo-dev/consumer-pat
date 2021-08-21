package br.com.alelo.consumer.consumerpat.mapper.entity;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.domain.AddressDomain;

public class AddressEntityMapper {

    public static AddressEntity convert(AddressDomain domain) {
        if (domain == null) {
            return null;
        }

        return AddressEntity.builder()
                .city(domain.getCity())
                .country(domain.getCountry())
                .number(domain.getNumber())
                .portalCode(domain.getPortalCode())
                .street(domain.getStreet())
                .build();
    }
}
