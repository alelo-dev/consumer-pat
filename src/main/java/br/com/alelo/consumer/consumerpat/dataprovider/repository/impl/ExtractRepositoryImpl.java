package br.com.alelo.consumer.consumerpat.dataprovider.repository.impl;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.repository.ExtractJpaRepository;
import br.com.alelo.consumer.consumerpat.core.domain.ExtractDomain;
import br.com.alelo.consumer.consumerpat.core.mapper.domain.ExtractDomainMapper;
import br.com.alelo.consumer.consumerpat.core.mapper.entity.ExtractEntityMapper;
import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.dataprovider.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExtractRepositoryImpl implements ExtractRepository {

    @Autowired
    private ExtractJpaRepository repository;

    @Override
    public ExtractDomain save(ExtractDomain domain) {
        ExtractEntity entity = ExtractEntityMapper.convert(domain);

        return ExtractDomainMapper.convert(this.repository.save(entity));
    }
}
