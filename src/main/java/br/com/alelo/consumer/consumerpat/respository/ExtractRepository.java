package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtractRepository extends JpaRepository<Extract, Long> {
    Optional<Extract> findByPurchaseCode(String purchaseCode);
}
