package br.com.alelo.consumerpat.dataprovider.repository.impl;

import br.com.alelo.consumerpat.core.domain.ContactDomain;
import br.com.alelo.consumerpat.core.mapper.domain.ContactDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.ContactEntityMapper;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ContactEntity;
import br.com.alelo.consumerpat.dataprovider.jpa.repository.ContactJpaRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private ContactJpaRepository repository;

    @Override
    public ContactDomain save(ContactDomain domain) {
        ContactEntity entity = ContactEntityMapper.convert(domain);

        return ContactDomainMapper.convert(this.repository.save(entity));
    }
}
