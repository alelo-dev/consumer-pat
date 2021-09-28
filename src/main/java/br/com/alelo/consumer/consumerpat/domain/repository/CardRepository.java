package br.com.alelo.consumer.consumerpat.domain.repository;

import br.com.alelo.consumer.consumerpat.domain.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository  extends JpaRepository<CardEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from Card where number = ?")
    Optional<CardEntity> findByNumber(int cardNumber);
}
