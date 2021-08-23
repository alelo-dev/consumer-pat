package br.com.alelo.consumerpat.dataprovider.jpa.repository;

import br.com.alelo.consumerpat.dataprovider.jpa.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Long> {

    ConsumerEntity findByConsumerCode(String consumerCode);

    ConsumerEntity findByDocument(String document);
}
