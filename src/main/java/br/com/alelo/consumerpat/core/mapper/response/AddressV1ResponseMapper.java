package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.dataprovider.entity.AddressEntity;
import br.com.alelo.consumerpat.core.dto.v1.response.AddressV1ResponseDto;

public class AddressV1ResponseMapper {

    public static AddressV1ResponseDto convert(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return AddressV1ResponseDto.builder()
                .city(addressEntity.getCity())
                .country(addressEntity.getCountry())
                .number(addressEntity.getNumber())
                .postalCode(addressEntity.getPostalCode())
                .street(addressEntity.getStreet())
                .build();
    }
}
