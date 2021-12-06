package br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

}
