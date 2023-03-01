package br.com.alelo.consumer.consumerpat.common.adapter.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer>, LoadCardPort {


}
