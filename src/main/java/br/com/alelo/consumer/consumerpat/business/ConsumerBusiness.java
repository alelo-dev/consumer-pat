package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import br.com.alelo.consumer.consumerpat.contants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.InvalidOperationException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsumerBusiness {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private CardBusiness cardBusiness;

    @Transactional
    public void saveOrUpdate(Consumer consumer) {
        repository.save(consumer);
    }

    public Page<Consumer> getAllConsumers(Pageable p) {
        return repository.findAll(p);
    }

    public Consumer getById(Integer id){
        Optional<Consumer> consumer = repository.findById(id);

        if(consumer.isEmpty())
            throw new EntityNotFoundException(new ErrorResponse("Consumer não encontrado"));

        return consumer.get();
    }

    public Consumer getConsumerByDocumentNumber(String documentNumber) {
        Optional<Consumer> consumer = repository.getConsumerByDocumentNumber(documentNumber);

        if (consumer.isEmpty())
            throw new EntityNotFoundException(new ErrorResponse("Consumer não encontrado"));

        return consumer.get();
    }

    /**
     * @param documentNumber
     * @param cardType
     * @param cardNumber
     *
     * Valida se já existe cartão de um determinado tipo, para que não haja dois cartões do mesmo tipo
     * Valida se já existe um cartão com o mesmo número e tipo
     */
    @Transactional
    public void addConsumerCard(String documentNumber, CardType cardType, String cardNumber) {
        Consumer consumer = getConsumerByDocumentNumber(documentNumber);

        boolean existCardType = consumer.getCards().stream().anyMatch(c -> c.getCardType().equals(cardType));

        if (existCardType)
            throw new InvalidOperationException(new ErrorResponse(String.format("Consumer já possui um cartão do tipo %s", cardType.toValue())));

        if(cardBusiness.existCard(cardNumber, cardType))
            throw new InvalidOperationException(new ErrorResponse(String.format("Cartão já existente")));

        consumer.addCard(Card
                .builder()
                .cardNumber(cardNumber)
                .cardType(cardType)
                .balance(0d)
                .consumer(consumer)
                .build());
    }

}
