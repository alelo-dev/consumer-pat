package br.com.alelo.consumer.consumerpat.dataprovider.jpa.repository;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardNumber(String cardNumber);

    List<CardEntity> findByCardNumberIn(List<String> cardsNumber);
}
