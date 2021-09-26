package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.CardConsumer;

@Repository
public interface CardConsumerRepository extends JpaRepository<CardConsumer, Long> {


	@Query("SELECT a FROM CardConsumer a WHERE a.cardNumber = :cardNumber AND a.cardType.id = :cardType")
	CardConsumer findByNumberAndType(@Param("cardNumber") String cardNumber, @Param("cardType") Long cardType);
	
	@Query("SELECT a FROM CardConsumer a WHERE a.cardNumber = :cardNumber AND a.consumer.id = :consumerId")
	CardConsumer findByNumberAndConsumer(@Param("cardNumber") String cardNumber, @Param("consumerId") Long consumerId);
	
	@Query("SELECT a FROM CardConsumer a WHERE a.cardNumber = :cardNumber")
	CardConsumer findByCardNumber(@Param("cardNumber") String cardNumber);
	
	
}
