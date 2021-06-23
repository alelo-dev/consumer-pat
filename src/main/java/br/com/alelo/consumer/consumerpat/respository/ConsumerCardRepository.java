package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.ConsumerCard;

@Repository
public interface ConsumerCardRepository extends JpaRepository<ConsumerCard, Long> {

	Optional<ConsumerCard> findByCardNumber(String cardNumber);

}