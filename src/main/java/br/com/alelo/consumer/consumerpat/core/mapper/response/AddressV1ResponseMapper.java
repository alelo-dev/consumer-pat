package br.com.alelo.consumer.consumerpat.core.mapper.response;

import br.com.alelo.consumer.consumerpat.core.domain.AddressDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.response.AddressV1ResponseDto;

public class AddressV1ResponseMapper {

    public static AddressV1ResponseDto convert(AddressDomain addressDomain) {
        if (addressDomain == null) {
            return null;
        }

        return AddressV1ResponseDto.builder()
                .city(addressDomain.getCity())
                .country(addressDomain.getCountry())
                .number(addressDomain.getNumber())
                .postalCode(addressDomain.getPostalCode())
                .street(addressDomain.getStreet())
                .build();
    }
}
