package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface CardsRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Cards c"
    		+ " join c.Consumer co  "
    		+ " where ca.cardNumber = :cardNumber")
    Cards findByCardNumber(@Param("ano") int cardNumber);

}
