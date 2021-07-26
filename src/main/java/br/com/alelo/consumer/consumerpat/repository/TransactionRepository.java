package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
