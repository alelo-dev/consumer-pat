package br.com.alelo.consumer.consumerpat.integration.respository;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

}
