package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.AddressRepository;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Card;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConsumerService {

    private ConsumerRepository consumerRepository;
    private CardRepository cardRepository;
    private AddressRepository addressRepository;

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

    private void create(Consumer newConsumer) {
        consumerRepository.save(newConsumer);
        newConsumer.getAddress().forEach(address -> address.setConsumer(newConsumer));
        addressRepository.saveAll(newConsumer.getAddress());
        newConsumer.getCards().forEach(card -> card.setConsumer(newConsumer));
        cardRepository.saveAll(newConsumer.getCards());
    }

    private void update(Consumer updateConsumer) {
        Consumer oldConsumer  = getConsumer(updateConsumer.getId());

        updateConsumer.getCards().forEach(updateCard -> {
            Optional<Card> oldCardOptional = oldConsumer.getCards().stream()
                    .filter(oldCard -> oldCard.getNumber().equals(updateCard.getNumber()))
                    .findFirst();

            if (oldCardOptional.isPresent()) {
                updateCard.setBalance(oldCardOptional.get().getBalance());
            } else {
                updateCard.setConsumer(updateConsumer);
                cardRepository.save(updateCard);
            }
        });
        consumerRepository.save(updateConsumer);
    }

    private Consumer getConsumer(int id) throws EntityNotFoundException {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);

        if (consumerOptional.isEmpty()) {
            throw new EntityNotFoundException("Cliente não localizado");
        }
        return consumerOptional.get();
    }

    public Card getCard(String cardNumber, int consumerId) throws EntityNotFoundException {
        Optional<Card> cardOptional = cardRepository.findCardByNumberAndConsumerId(cardNumber, consumerId);

        if (cardOptional.isEmpty()) {
            throw new EntityNotFoundException("Carão não localizado");
        }
        return cardOptional.get();
    }

    @Transactional
    public void cardRecharge(int consumerId, String cardNumber, BigDecimal amount) {
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("valor deve ser maior que zero");
        }
        Card card = getCard(cardNumber, consumerId);
        card.setBalance(card.getBalance().add(amount));
        cardRepository.save(card);
    }

    public boolean verifyExistsConsumerById(int id) {
        return consumerRepository.existsById(id);
    }
}
