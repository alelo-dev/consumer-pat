package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
