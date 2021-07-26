package br.com.alelo.consumer.consumerpat.respository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 12:20
 */

import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerAleloCardRepository extends JpaRepository<ConsumerAleloCard, Integer>, IRepository<ConsumerAleloCard> {

    Optional<ConsumerAleloCard> findByNumber(String cardNumber);

}
