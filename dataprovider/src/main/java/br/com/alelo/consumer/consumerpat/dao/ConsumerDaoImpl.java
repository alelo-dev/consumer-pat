package br.com.alelo.consumer.consumerpat.dao;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
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
}
