package br.com.alelo.consumerpat.dataprovider.repository.impl;


import br.com.alelo.consumerpat.core.domain.AddressDomain;
import br.com.alelo.consumerpat.core.mapper.domain.AddressDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.AddressEntityMapper;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.AddressEntity;
import br.com.alelo.consumerpat.dataprovider.jpa.repository.AddressJpaRepository;
import br.com.alelo.consumerpat.dataprovider.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    private AddressJpaRepository repository;

    @Override
    public AddressDomain save(AddressDomain domain) {
        AddressEntity entity = AddressEntityMapper.convert(domain);

        return AddressDomainMapper.convert(this.repository.save(entity));
    }
}
