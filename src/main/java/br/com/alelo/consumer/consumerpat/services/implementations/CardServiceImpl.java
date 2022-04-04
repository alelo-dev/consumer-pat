package br.com.alelo.consumer.consumerpat.services.implementations;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.services.CardService;


@Service
public class CardServiceImpl implements CardService {
	
    @Autowired
    CardRepository cardRepository;

    
	@Override
	public Card save(Card card) {
		
		return cardRepository.save( card );
	}


	@Override
	public Optional<Card> getCardById( long id ) {
		
		return cardRepository.findById( id );
	}


	@Override
	public Card getCardByNumber(int cardNumber) {
		
		return cardRepository.findByNumber( cardNumber );
	}
	
}