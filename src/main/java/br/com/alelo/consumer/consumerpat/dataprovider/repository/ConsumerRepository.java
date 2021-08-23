package br.com.alelo.consumer.consumerpat.dataprovider.repository;

import br.com.alelo.consumer.consumerpat.core.domain.ConsumerDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerRepository extends BaseRepository<ConsumerDomain> {

    Page<ConsumerDomain> findAll(Pageable pageable);

    ConsumerDomain findByConsumerCode(String customerCode);

    ConsumerDomain findByDocument(String document);
}
