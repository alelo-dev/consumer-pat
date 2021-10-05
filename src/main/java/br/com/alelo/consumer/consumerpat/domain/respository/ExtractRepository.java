package br.com.alelo.consumer.consumerpat.domain.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, Long> {

}
