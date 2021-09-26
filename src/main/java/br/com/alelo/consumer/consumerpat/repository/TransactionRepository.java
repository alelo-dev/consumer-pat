package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
