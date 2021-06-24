package br.com.alelo.consumer.consumerpat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.model.entity.ConsumerCard;

@Repository
public interface ConsumerCardRepository extends JpaRepository<ConsumerCard, Long> {

	Optional<ConsumerCard> findByCardNumber(String cardNumber);

}