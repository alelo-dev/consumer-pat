package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerRepository extends BaseRepository<ConsumerDomain> {

    Page<ConsumerDomain> findAll(Pageable pageable);

    ConsumerDomain findByConsumerCode(String customerCode);

    ConsumerDomain findByDocument(String document);
}
