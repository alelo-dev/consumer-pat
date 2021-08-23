package br.com.alelo.consumerpat.dataprovider.repository.impl;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.ConsumerEntityMapper;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ConsumerEntity;
import br.com.alelo.consumerpat.dataprovider.jpa.repository.ConsumerJpaRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerRepositoryImpl implements ConsumerRepository {

    @Autowired
    private ConsumerJpaRepository repository;

    @Override
    public ConsumerDomain save(ConsumerDomain domain) {
        ConsumerEntity entity = ConsumerEntityMapper.convertOnlyConsumer(domain);

        return ConsumerDomainMapper.convert(this.repository.save(entity));
    }

    @Override
    public Page<ConsumerDomain> findAll(Pageable pageable) {
        return this.repository.findAll(pageable).map(ConsumerDomainMapper::convert);
    }

    @Override
    public ConsumerDomain findByConsumerCode(String consumerCode) {
        return ConsumerDomainMapper.convert(this.repository.findByConsumerCode(consumerCode));
    }

    @Override
    public ConsumerDomain findByDocument(String document) {
        return ConsumerDomainMapper.convert(this.repository.findByDocument(document));
    }
}
