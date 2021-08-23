package br.com.alelo.consumer.consumerpat.core.mapper.domain;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.core.domain.AddressDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.AddressV1RequestDto;

public class AddressDomainMapper {

    public static AddressDomain convert(AddressV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return AddressDomain.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .number(request.getNumber())
                .postalCode(request.getPostalCode())
                .street(request.getStreet())
                .build();
    }

    public static AddressDomain convert(AddressV1RequestDto request, AddressDomain addressDomain) {
        if (request == null || addressDomain == null) {
            return null;
        }

        return AddressDomain.builder()
                .id(addressDomain.getId())
                .city(request.getCity())
                .country(request.getCountry())
                .number(request.getNumber())
                .postalCode(request.getPostalCode())
                .street(request.getStreet())
                .build();
    }

    public static AddressDomain convert(AddressEntity entity) {
        if (entity == null) {
            return null;
        }

        return AddressDomain.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .country(entity.getCountry())
                .number(entity.getNumber())
                .postalCode(entity.getPostalCode())
                .street(entity.getStreet())
                .consumer(ConsumerDomainMapper.convertOnlyConsumer(entity.getConsumer()))
                .build();
    }
}
