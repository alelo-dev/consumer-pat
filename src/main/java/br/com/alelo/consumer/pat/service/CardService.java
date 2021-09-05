package br.com.alelo.consumer.pat.service;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.entity.Consumer;
import br.com.alelo.consumer.pat.exception.AlreadyExistsCardsException;
import br.com.alelo.consumer.pat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.pat.exception.InvalidCardTypeException;
import br.com.alelo.consumer.pat.payload.CardPayload;
import br.com.alelo.consumer.pat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CardService {

    private final ConsumerRepository consumerRepository;

    public List<CardPayload> findByConsumerId(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(ConsumerNotFoundException::new);

        List<Card> cards = consumer.getCards();

        return cards.stream().map(CardPayload::from).collect(Collectors.toList());
    }

    public void createCards(final Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(ConsumerNotFoundException::new);

        List<Card> existentCards = consumer.getCards();

        List newCards = Stream.of(EstablishmentType.values())
            .filter(et -> existentCards.stream().allMatch(c -> c.getEstablishmentType() != et))
            .map(Card::fromEstablishmentType)
            .collect(Collectors.toList());

        if (newCards.isEmpty()) {
            throw new AlreadyExistsCardsException("Consumer já possui todos tipos de cartões!");
        }

        consumer.getCards().addAll(newCards);

        consumerRepository.save(consumer);
    }

    public void createCardByType(final Long consumerId, final EstablishmentType cardType) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(ConsumerNotFoundException::new);

        List<Card> existentCards = consumer.getCards();

        if (existentCards.stream().anyMatch(card -> card.getEstablishmentType() == cardType)) {
            throw new InvalidCardTypeException("Tipo de cartão já existe!");
        }

        existentCards.add(Card.fromEstablishmentType(cardType));

        consumerRepository.save(consumer);
    }

}
