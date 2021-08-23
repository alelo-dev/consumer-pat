package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ContactEntity;
import br.com.alelo.consumerpat.core.domain.ContactDomain;

public class ContactEntityMapper {

    public static ContactEntity convert(ContactDomain domain) {
        if (domain == null) {
            return null;
        }

        return ContactEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .mobilePhone(domain.getMobilePhone())
                .residencePhone(domain.getResidencePhone())
                .consumer(ConsumerEntityMapper.convertOnlyConsumer(domain.getConsumer()))
                .build();
    }
}
