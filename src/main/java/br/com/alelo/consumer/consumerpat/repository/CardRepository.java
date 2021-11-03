package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	Card findByNumber(String number);
	
}
