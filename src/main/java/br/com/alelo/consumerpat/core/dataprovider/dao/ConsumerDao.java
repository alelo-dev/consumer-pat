package br.com.alelo.consumerpat.core.dataprovider.dao;

import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerDao extends BaseDao<ConsumerEntity> {

    Page<ConsumerEntity> findAll(Pageable pageable);

    ConsumerEntity findByConsumerCode(String customerCode);

    ConsumerEntity findByDocument(String document);
}
