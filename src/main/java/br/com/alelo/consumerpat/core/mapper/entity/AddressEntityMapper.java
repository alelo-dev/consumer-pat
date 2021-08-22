package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.dataprovider.entity.AddressEntity;
import br.com.alelo.consumerpat.core.domain.AddressDomain;

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
