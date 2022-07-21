package br.com.alelo.consumer.consumerpat.domain.service.impl;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerApplicationException;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidRequestException;
import br.com.alelo.consumer.consumerpat.domain.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Implementação da interface de gerenciamento de dados do consumidor.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public List<Consumer> findAllConsumers() {
        return (List<Consumer>) this.consumerRepository.findAll();
    }

    @Override
    public Consumer findConsumerById(BigInteger id) throws ConsumerApplicationException {
        return this.consumerRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::ofConsumerNotFoundException);
    }

    @Override
    public void verifyIfConsumerAlreadyExists(Consumer consumer) throws ConsumerApplicationException {
        Consumer fetchedConsumer = this.consumerRepository.findByDrugstoreCardNumberOrFoodCardNumberOrFuelCardNumber(
                consumer.getDrugstoreCardNumber(),
                consumer.getFoodCardNumber(),
                consumer.getFuelCardNumber());

        if (fetchedConsumer != null) {
            throw InvalidRequestException.ofConsumerAlreadyRegisteredException();
        }
    }

    @Override
    public Consumer findConsumerByCardNumber(BigInteger cardNumber, CardType cardType) throws ConsumerApplicationException {
        return this.consumerRepository
                .findByCardNumber(cardNumber, cardType)
                .orElseThrow(ResourceNotFoundException::ofConsumerNotFoundException);
    }

    private Consumer createConsumer(Consumer consumer) throws ConsumerApplicationException {
        this.verifyIfConsumerAlreadyExists(consumer);
        consumer = this.consumerRepository.save(consumer);
        return consumer;
    }

    private Consumer updateConsumer(Consumer consumer) throws ConsumerApplicationException {

        Consumer fetchedConsumer = this.findConsumerById(consumer.getId());

        if (fetchedConsumer.hasCardBalanceChanged(consumer)) {
            throw InvalidRequestException.ofCardBalanceCannotBeChangedException();
        }

        fetchedConsumer.selfUpdate(consumer);
        this.consumerRepository.save(fetchedConsumer);

        return fetchedConsumer;
    }

    @Override
    public Consumer saveConsumer(Consumer consumer) throws ConsumerApplicationException {
        if (consumer != null) {
            if (consumer.getId() == null) {
                consumer = createConsumer(consumer);
            } else {
                consumer = updateConsumer(consumer);
            }
        }
        return consumer;
    }

    @Override
    public void deleteConsumer(BigInteger id) throws ConsumerApplicationException {
        try {
            this.consumerRepository.deleteById(id);
        } catch (Exception e) {
            throw InvalidRequestException.ofFailedToDeleteConsumerException();
        }
    }

    @Override
    public Consumer setConsumersCardBalance(BigInteger cardNumber, BigDecimal value, CardType cardType)
            throws ConsumerApplicationException {

        Optional<Consumer> optionalConsumer = this.consumerRepository
                .findByCardNumber(cardNumber, cardType);

        if (optionalConsumer.isEmpty()) {
            throw InvalidRequestException.ofBenefitCardNotFound();
        }

        Consumer consumer = optionalConsumer.get();
        consumer.setBalance(value, cardType);

        return consumerRepository.save(consumer);
    }

}
