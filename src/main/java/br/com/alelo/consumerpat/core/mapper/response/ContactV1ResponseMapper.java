package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.dataprovider.entity.ContactEntity;
import br.com.alelo.consumerpat.core.v1.response.ContactV1ResponseDto;

public class ContactV1ResponseMapper {

    public static ContactV1ResponseDto convert(ContactEntity contactEntity) {
        if (contactEntity == null) {
            return null;
        }

        return ContactV1ResponseDto.builder()
                .email(contactEntity.getEmail())
                .mobilePhone(contactEntity.getMobilePhone())
                .residencePhone(contactEntity.getResidencePhone())
                .build();
    }
}
