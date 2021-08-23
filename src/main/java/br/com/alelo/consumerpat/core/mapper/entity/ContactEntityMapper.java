package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.dataprovider.entity.ContactEntity;
import br.com.alelo.consumerpat.core.domain.ContactDomain;

public class ContactEntityMapper {

    public static ContactEntity convert(ContactDomain domain) {
        if (domain == null) {
            return null;
        }

        return ContactEntity.builder()
                .email(domain.getEmail())
                .mobilePhone(domain.getMobilePhone())
                .residencePhone(domain.getResidencePhone())
                .build();
    }
}
