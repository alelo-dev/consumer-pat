package br.com.alelo.consumer.consumerpat.respository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.model.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Integer> {

	@Transactional(readOnly=true)
	Cards findByCardNumber(BigInteger cardNumber);

}
