package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CardCustomerRepository extends JpaRepository<CardCustomerEntity, UUID> {
    Set<CardCustomerEntity> findByCustomerId(UUID id);
    Optional<CardCustomerEntity> findByCardNumber(String cardNumber);
}
