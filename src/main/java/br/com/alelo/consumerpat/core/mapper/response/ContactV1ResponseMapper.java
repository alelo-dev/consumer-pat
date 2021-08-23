package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.domain.ContactDomain;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ContactEntity;
import br.com.alelo.consumerpat.core.dto.v1.response.ContactV1ResponseDto;

public class ContactV1ResponseMapper {

    public static ContactV1ResponseDto convert(ContactDomain contactDomain) {
        if (contactDomain == null) {
            return null;
        }

        return ContactV1ResponseDto.builder()
                .email(contactDomain.getEmail())
                .mobilePhone(contactDomain.getMobilePhone())
                .residencePhone(contactDomain.getResidencePhone())
                .build();
    }
}
