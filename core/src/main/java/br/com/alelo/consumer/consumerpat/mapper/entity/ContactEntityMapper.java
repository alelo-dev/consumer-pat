package br.com.alelo.consumer.consumerpat.mapper.entity;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.domain.ContactDomain;

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
