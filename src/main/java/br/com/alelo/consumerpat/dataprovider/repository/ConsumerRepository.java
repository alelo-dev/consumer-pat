package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.dataprovider.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {

    ConsumerEntity findByConsumerCode(String consumerCode);

    ConsumerEntity findByDocument(String document);
}
