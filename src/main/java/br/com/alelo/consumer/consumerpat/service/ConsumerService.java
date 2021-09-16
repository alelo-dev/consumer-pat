package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import br.com.alelo.consumer.consumerpat.data.entity.Consumer;
import br.com.alelo.consumer.consumerpat.data.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.data.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidIdException;
import br.com.alelo.consumer.consumerpat.domain.exception.UnknownConsumerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.alelo.consumer.consumerpat.domain.Industry.*;
import static java.math.BigDecimal.ZERO;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    CardRepository cardRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    public Page<Consumer> listAllConsumers(int size, int page) {
        return consumerRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    /* Cadastrar novos clientes */
    public Consumer createConsumer(Consumer consumer) {
        Consumer created = this.consumerRepository.save(consumer);
        Integer consumerId = created.getId();
        Card foodCard = new Card().setConsumerId(consumerId).setNumber(UUID.randomUUID().toString()).setBalance(ZERO).setType(FOOD);
        Card fuelCard = new Card().setConsumerId(consumerId).setNumber(UUID.randomUUID().toString()).setBalance(ZERO).setType(FUEL);
        Card drugstoreCard = new Card().setConsumerId(consumerId).setNumber(UUID.randomUUID().toString()).setBalance(ZERO).setType(DRUGSTORE);
        cardRepository.save(foodCard);
        cardRepository.save(fuelCard);
        cardRepository.save(drugstoreCard);
        return created;
    }

    // Não deve ser possível alterar o saldo do cartão
    public Consumer updateConsumer(Consumer consumer) throws InvalidIdException, UnknownConsumerException {
        if (consumer.getId() == null) throw new InvalidIdException();
        if (!consumerRepository.existsById(consumer.getId())) throw new UnknownConsumerException();
        return consumerRepository.save(consumer);
    }

    public Consumer findById(Integer consumerId) throws UnknownConsumerException {
        return consumerRepository.findById(consumerId).orElseThrow(UnknownConsumerException::new);
    }
}
