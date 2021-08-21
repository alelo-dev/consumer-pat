package br.com.alelo.consumer.consumerpat.dataprovider.dao;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerDao extends BaseDao<ConsumerEntity> {

    Page<ConsumerEntity> findAll(Pageable pageable);

    ConsumerEntity findByConsumerCode(String customerCode);
}
