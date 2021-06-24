package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.StatementAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementAccountRepository extends JpaRepository<StatementAccount, Integer> {
}