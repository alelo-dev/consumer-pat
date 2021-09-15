package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(nativeQuery = true, value = "select * from Card where NUMBER = :number ")
    Optional<Card> findByNumber(@Param("number") int number);

    @Query(nativeQuery = true, value = "select * from Card where TYPE = :type AND NUMBER = :number ")
    Optional<Card> findByTypeAndNumber(@Param("type") String type, @Param("number") int number);

}
