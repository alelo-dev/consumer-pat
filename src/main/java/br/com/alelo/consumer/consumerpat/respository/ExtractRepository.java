package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.data.model.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
