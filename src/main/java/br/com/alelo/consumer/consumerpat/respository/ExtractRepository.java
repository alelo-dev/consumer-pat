package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<Purchase, Integer> {
}
