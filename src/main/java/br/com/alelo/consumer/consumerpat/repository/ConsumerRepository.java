package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("select c from Consumer c")
    Page<Consumer> getAllConsumersList(Pageable pageable);

    Optional<Consumer> findByIdentifier(String identifier);

    Optional<Consumer> findByFoodCardNumber(long cardNumber);

    Optional<Consumer> findByFuelCardNumber(long cardNumber);

    Optional<Consumer> findByDrugstoreNumber(long cardNumber);

    @Query("select case when count(c) > 0 then true else false end from Consumer c " +
            " where c.documentNumber = ?1 and c.id != ?2")
    boolean existsByDocumentNumber(long documentNumber, int id);

    @Query("select case when count(c) > 0 then true else false end from Consumer c " +
            " where (c.foodCardNumber = ?1 or " +
            " c.fuelCardNumber = ?1 or " +
            " c.drugstoreNumber = ?1) and c.id != ?2")
    boolean existsByCardNumber(long cardNumber, int id);

    @Query(value = "select c from Consumer c " +
            " where (c.foodCardNumber = ?1 or " +
            " c.fuelCardNumber = ?1 or " +
            " c.drugstoreNumber = ?1) and c.id != ?2")
    Optional<Consumer> findByCardNumber(long cardNumber, int id);

}