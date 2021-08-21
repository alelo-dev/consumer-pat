package br.com.alelo.consumer.consumerpat.mapper.domain;

import br.com.alelo.consumer.consumerpat.domain.AddressDomain;
import br.com.alelo.consumer.consumerpat.dto.v1.request.AddressV1RequestDto;

public class AddressDomainMapper {

    public static AddressDomain convert(AddressV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return AddressDomain.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .number(request.getNumber())
                .portalCode(request.getPortalCode())
                .street(request.getStreet())
                .build();
    }
}
