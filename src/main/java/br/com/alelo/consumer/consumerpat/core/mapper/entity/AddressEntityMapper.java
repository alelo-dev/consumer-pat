package br.com.alelo.consumer.consumerpat.core.mapper.entity;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.core.domain.AddressDomain;

public class AddressEntityMapper {

    public static AddressEntity convert(AddressDomain domain) {
        if (domain == null) {
            return null;
        }

        return AddressEntity.builder()
                .id(domain.getId())
                .city(domain.getCity())
                .country(domain.getCountry())
                .number(domain.getNumber())
                .postalCode(domain.getPostalCode())
                .street(domain.getStreet())
                .consumer(ConsumerEntityMapper.convertOnlyConsumer(domain.getConsumer()))
                .build();
    }
}
