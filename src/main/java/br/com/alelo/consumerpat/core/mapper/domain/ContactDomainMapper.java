package br.com.alelo.consumerpat.core.mapper.domain;

import br.com.alelo.consumerpat.core.domain.ContactDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.ContactV1RequestDto;

public class ContactDomainMapper {

    public static ContactDomain convert(ContactV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return ContactDomain.builder()
                .email(request.getEmail())
                .mobilePhone(request.getMobilePhone())
                .residencePhone(request.getResidencePhone())
                .build();
    }
}
