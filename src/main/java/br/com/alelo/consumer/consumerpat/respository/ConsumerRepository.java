package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("select c.consumer from Card c where c.cardNumber=:cardNumber and c.cardType=:cardType")
    Consumer findByCardNumber(int cardNumber, int cardType);
}
