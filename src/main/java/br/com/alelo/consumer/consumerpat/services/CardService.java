package br.com.alelo.consumer.consumerpat.services;
import java.util.Optional;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardService {

	Card save(Card card);
	
	Optional<Card> getCardById( long id);

	Card getCardByNumber(int cardNumber);
	
}