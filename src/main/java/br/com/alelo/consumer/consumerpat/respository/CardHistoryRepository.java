package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.CardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHistoryRepository extends JpaRepository<CardHistory, Integer> {

}
