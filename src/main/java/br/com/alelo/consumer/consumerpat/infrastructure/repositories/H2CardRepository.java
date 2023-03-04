package br.com.alelo.consumer.consumerpat.infrastructure.repositories;

import br.com.alelo.consumer.consumerpat.domain.entities.Card;
import br.com.alelo.consumer.consumerpat.domain.repositories.CardRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.CardEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.jpas.CardJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class H2CardRepository implements CardRepository {

    private final CardJpaRepository cardRepository;

    @Autowired
    public H2CardRepository(final CardJpaRepository cardJpaRepository) {
        this.cardRepository = cardJpaRepository;
    }

    @Override
    public Optional<Card> findByCardNumberAndConsumerId(String cardNumber, int consumerId) {
        Optional<Card> card = Optional.empty();

        Optional<CardEntity> cardEntity = cardRepository.findByCardNumberAndConsumerId(cardNumber, consumerId);
        if (cardEntity.isPresent()) {
            card = Optional.of(cardEntity.get().toModel());
        }
        return card;
    }

    @Override
    public void updateBalance(Card card) {
        Optional<CardEntity> cardEntity = cardRepository.findById(card.getId());

        cardEntity.ifPresent(entity -> entity.setBalance(card.getBalance()));
        cardRepository.save(cardEntity.get());
    }
}
