package br.com.alelo.consumer.consumerpat.respository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.CardTransaction;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long>{

	Page<CardTransaction> findAllByCardNumber(String cardNumber, Pageable pageable);
}
