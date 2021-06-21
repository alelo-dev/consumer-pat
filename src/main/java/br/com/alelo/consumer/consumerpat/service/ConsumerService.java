package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Statement;
import br.com.alelo.consumer.consumerpat.parameter.BuyParameter;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConsumerService {

    @Value("${msg.consumer_not_found}")
    private String CONSUMER_NOT_FOUND;

    @Value("${msg.card_not_found}")
    private String CARD_NOT_FOUND;

    @Value("${msg.establishment_not_found}")
    private String ESTABLISHMENT_NOT_FOUND;

    private ConsumerRepository consumerRepository;
    private StatementRepository statementRepository;
    private CardRepository cardRepository;
    private EstablishmentRepository establishmentRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository, StatementRepository statementRepository, CardRepository cardRepository, EstablishmentRepository establishmentRepository) {
        this.consumerRepository = consumerRepository;
        this.statementRepository = statementRepository;
        this.cardRepository = cardRepository;
        this.establishmentRepository = establishmentRepository;
    }

    public Page<Consumer> findAllConsumers(Pageable pageable) {
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

        Optional<Card> cardOptional = cardRepository.findByNumberAndConsumerId(cardNumber, consumerId);

        if (cardOptional.isEmpty()) {
            throw new EntityNotFoundException(CARD_NOT_FOUND+cardNumber);
        }

        Card card = cardOptional.get();
        card.setBalance(card.getBalance().add(valueToAdd));

        cardRepository.save(card);
    }

    private Consumer getConsumerOrException(int id) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);

        if (consumerOptional.isEmpty()) {
            throw new EntityNotFoundException(CONSUMER_NOT_FOUND);
        }

        return consumerOptional.get();
    }

    public void buy(BuyParameter parameter) {
        if (!consumerRepository.existsById(parameter.getConsumerId())) {
            throw new EntityNotFoundException(CONSUMER_NOT_FOUND);
        }

        if (!establishmentRepository.existsById(parameter.getEstablishmentId())) {
            throw new EntityNotFoundException(ESTABLISHMENT_NOT_FOUND);
        }

        Consumer consumer = consumerRepository.getOne(parameter.getConsumerId());

        Statement statement = new Statement();
        statement.setConsumer(consumer);
        statement.setEstablishment(establishmentRepository.getOne(parameter.getEstablishmentId()));
        statement.setCardNumber(parameter.getCardNumber());
        statement.setProductDescription(parameter.getProductDescription());
        statement.setValue(parameter.getProductValue());

        consumerRepository.save(consumer);
        statementRepository.save(statement);
    }
}
