package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.CardEntity;

public interface ICardEntityRepository extends JpaRepository<CardEntity, Integer> {

    CardEntity findByNumberAndType (Integer number, Integer type);
    
}
