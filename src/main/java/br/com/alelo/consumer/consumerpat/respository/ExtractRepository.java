package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.model.Extract;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
