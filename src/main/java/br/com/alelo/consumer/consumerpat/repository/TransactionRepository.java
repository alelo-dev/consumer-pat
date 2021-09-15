package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
