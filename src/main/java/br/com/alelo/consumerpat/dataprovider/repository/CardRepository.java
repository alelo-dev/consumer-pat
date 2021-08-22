package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardNumber(String cardNumber);
}
