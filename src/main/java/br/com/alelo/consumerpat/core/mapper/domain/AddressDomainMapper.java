package br.com.alelo.consumerpat.core.mapper.domain;

import br.com.alelo.consumerpat.core.domain.AddressDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.AddressV1RequestDto;

public class AddressDomainMapper {

    public static AddressDomain convert(AddressV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return AddressDomain.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .number(request.getNumber())
                .portalCode(request.getPostalCode())
                .street(request.getStreet())
                .build();
    }
}
