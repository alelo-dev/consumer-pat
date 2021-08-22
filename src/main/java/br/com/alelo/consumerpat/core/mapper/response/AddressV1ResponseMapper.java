package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.dataprovider.entity.AddressEntity;
import br.com.alelo.consumerpat.core.v1.response.AddressV1ResponseDto;

public class AddressV1ResponseMapper {

    public static AddressV1ResponseDto convert(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return AddressV1ResponseDto.builder()
                .city(addressEntity.getCity())
                .country(addressEntity.getCountry())
                .number(addressEntity.getNumber())
                .portalCode(addressEntity.getPortalCode())
                .street(addressEntity.getStreet())
                .build();
    }
}
