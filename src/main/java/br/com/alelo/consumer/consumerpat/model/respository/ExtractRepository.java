package br.com.alelo.consumer.consumerpat.model.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Long> {
}
