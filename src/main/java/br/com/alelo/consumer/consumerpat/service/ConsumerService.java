package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.Messages;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConsumerService {

    private ConsumerRepository consumerRepository;
    private CardRepository cardRepository;
    private Messages messages;

    public Page<Consumer> findAll(Pageable pageable) {
        return consumerRepository.findAll(pageable);
    }

    @Transactional
    public void save(Consumer consumer) {
        if (consumer.getId() == null) {
            create(consumer);
        } else {
            update(consumer);
        }
    }

    private void create(Consumer consumer) {
        consumerRepository.save(consumer);
        consumer.getCards().forEach(card -> card.setConsumer(consumer));
        cardRepository.saveAll(consumer.getCards());
    }

    private void update(Consumer updatedConsumer) {
        preventBalanceUpdatingAndCreateNews(getConsumerOrException(updatedConsumer.getId()), updatedConsumer);
        consumerRepository.save(updatedConsumer);
    }

    private void preventBalanceUpdatingAndCreateNews(Consumer formerConsumer, Consumer updatedConsumer) {
        updatedConsumer.getCards().forEach(updatedCard -> {

            Optional<Card> formerCardOptional = formerConsumer.getCards().stream()
                    .filter(formerCard -> formerCard.getNumber().equals(updatedCard.getNumber()))
                    .findFirst();

            if (formerCardOptional.isPresent()) {
                updatedCard.setBalance(formerCardOptional.get().getBalance());
            } else {
                updatedCard.setConsumer(updatedConsumer);
                cardRepository.save(updatedCard);
            }
        });
    }

    @Transactional
    public void addValueToCard(int consumerId, BigDecimal valueToAdd, String cardNumber) {
        Card card = getCardOrException(consumerId, cardNumber);
        card.setBalance(card.getBalance().add(valueToAdd));
        cardRepository.save(card);
    }

    public Card getCardOrException(int consumerId, String cardNumber) throws EntityNotFoundException {
        Optional<Card> cardOptional = cardRepository.findByNumberAndConsumerId(cardNumber, consumerId);

        if (cardOptional.isEmpty()) {
            throw new EntityNotFoundException(messages.cardNotFound +cardNumber);
        }

        return cardOptional.get();
    }

    public boolean existsConsumerById(int id) {
        return consumerRepository.existsById(id);
    }

    private Consumer getConsumerOrException(int id) throws EntityNotFoundException {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);

        if (consumerOptional.isEmpty()) {
            throw new EntityNotFoundException(messages.consumerNotFound);
        }

        return consumerOptional.get();
    }

}
