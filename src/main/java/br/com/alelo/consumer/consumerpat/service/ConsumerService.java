package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.utils.MaskUtils;
import br.com.alelo.consumer.consumerpat.validator.ConsumerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_NOT_FOUND;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;


@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    CardService cardService;

    @Autowired
    MessageService messageService;

    @Autowired
    ConsumerValidator consumerValidator;

    public void createConsumer(Consumer consumer) {

        adjustConsumer(consumer);
        consumerRepository.save(consumer);
    }

    public void updateConsumer(Consumer consumer) {

        adjustConsumer(consumer);
        final Optional<Consumer> checkPersistedConsumer =
                consumerRepository.findConsumerByDocumentNumber(consumer.getDocumentNumber());
        if (checkPersistedConsumer.isEmpty()) {
            throw new CustomException(messageService.get(CONSUMER_NOT_FOUND.getMessage(), "document number",
                    consumer.getDocumentNumber()), HttpStatus.NOT_FOUND,
                    CONSUMER_NOT_FOUND.getCode());
        }

        final Consumer persistedConsumer = checkPersistedConsumer.get();

        consumer.setId(persistedConsumer.getId());

        updateConsumerCards(consumer.getCards(), persistedConsumer.getCards());

        consumerRepository.save(consumer);
    }

    //protected para teste unitário
    protected void adjustConsumer(Consumer consumer) {

        consumerValidator.accept(consumer);
        consumer.setDocumentNumber(MaskUtils.removeDocumentNumberMask(consumer.getDocumentNumber()));
        consumer.getCards().forEach(card -> cardService.adjustCard(card));

    }

    //protected para teste unitário
    protected void updateConsumerCards(List<Card> updatedConsumerCards, List<Card> consumerPersistedCards) {
        if (isNotEmpty(updatedConsumerCards)) {
            final List<Card> discontinuedCards =
                    updatedConsumerCards.stream().filter(Card::isDiscontinued).collect(Collectors.toList());
            updatedConsumerCards.removeAll(discontinuedCards);

            //tirando cartões já existentes para travar mudança do saldo
            updatedConsumerCards.removeIf(card -> consumerPersistedCards.stream()
                    .anyMatch(consumerCard -> card.getNumber().equals(consumerCard.getNumber())));

            //atualizando status do cartão
            consumerPersistedCards.forEach(card ->
                    //deve ter o mesmo número e tipo senão é desconsiderado
                    discontinuedCards.stream()
                            .filter(discontinuedCard -> card.getNumber()
                                    .equals(discontinuedCard.getNumber()) && card.getType().equals(discontinuedCard.getType()))
                            .findFirst().ifPresent(match -> {
                                card.setDiscontinued(true);
                                discontinuedCards.remove(match);
                            }));

            updatedConsumerCards.addAll(consumerPersistedCards);
        }
    }

    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    public Consumer findById(final Long id) {

        Optional<Consumer> response = consumerRepository.findById(id);

        if (response.isPresent()) {
            return response.get();
        }

        throw new CustomException(messageService.get(CONSUMER_NOT_FOUND.getMessage(), "id", id), HttpStatus.NOT_FOUND,
                CONSUMER_NOT_FOUND.getCode());
    }

    public Consumer findConsumerByDocumentNumber(final String documentNumber) {

        Optional<Consumer> response = consumerRepository.findConsumerByDocumentNumber(documentNumber);

        if (response.isPresent()) {
            return response.get();
        }

        throw new CustomException(messageService.get(CONSUMER_NOT_FOUND.getMessage(), "document number",
                documentNumber), HttpStatus.NOT_FOUND, CONSUMER_NOT_FOUND.getCode());
    }
}
