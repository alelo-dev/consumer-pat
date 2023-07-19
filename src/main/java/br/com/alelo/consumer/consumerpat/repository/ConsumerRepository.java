package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    Page<Consumer> getAllConsumers(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from Consumer where " +
            "DRUGSTORE_NUMBER = :cardNumber " +
            "OR FUEL_CARD_NUMBER = :cardNumber " +
            "OR FOOD_CARD_NUMBER = :cardNumber ")
    Optional<Consumer> findByCardNumber(Integer cardNumber);
}
