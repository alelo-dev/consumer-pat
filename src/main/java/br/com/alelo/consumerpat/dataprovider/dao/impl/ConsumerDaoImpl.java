package br.com.alelo.consumerpat.dataprovider.dao.impl;

import br.com.alelo.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.dataprovider.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDaoImpl implements ConsumerDao {

    @Autowired
    private ConsumerRepository repository;

    @Override
    public ConsumerEntity save(ConsumerEntity entity) {
        return this.repository.save(entity);
    }

    @Override
    public Page<ConsumerEntity> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public ConsumerEntity findByConsumerCode(String consumerCode) {
        return this.repository.findByConsumerCode(consumerCode);
    }

    @Override
    public ConsumerEntity findByDocument(String document) {
        return this.repository.findByDocument(document);
    }
}
