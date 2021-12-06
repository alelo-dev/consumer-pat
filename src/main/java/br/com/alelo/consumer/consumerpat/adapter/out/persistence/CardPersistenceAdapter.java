package br.com.alelo.consumer.consumerpat.adapter.out.persistence;

import br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.application.port.out.FindCardPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateCardPort;
import br.com.alelo.consumer.consumerpat.common.PersistenceAdapter;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;

@PersistenceAdapter
public class CardPersistenceAdapter implements FindCardPort, UpdateCardPort {
  
  private CardRepository cardRepository;

  public CardPersistenceAdapter(final CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }
  
  @Override
  public Card find(Integer number) {
    
    var cardEntity = cardRepository.findById(number).orElseThrow(() -> new CardNotFoundException());
    return CardMapper.mapToDomain(cardEntity);
    
  }

  @Override
  public Card updateBalance(final Card card) {
        
    var cardEntity = cardRepository.findById(card.getNumber()).orElseThrow(() -> new CardNotFoundException());
    
    cardEntity.setBalance(card.getBalance());

    cardRepository.save(cardEntity);
    
    return card;

  }
}
