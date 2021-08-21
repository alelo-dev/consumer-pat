package br.com.alelo.consumer.consumerpat.mapper.domain;

import br.com.alelo.consumer.consumerpat.domain.ContactDomain;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ContactV1RequestDto;

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
