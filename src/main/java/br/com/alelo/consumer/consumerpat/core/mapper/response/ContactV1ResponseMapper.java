package br.com.alelo.consumer.consumerpat.core.mapper.response;

import br.com.alelo.consumer.consumerpat.core.domain.ContactDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.response.ContactV1ResponseDto;

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
