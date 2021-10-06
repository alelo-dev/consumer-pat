package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static final String CONSUMER_SERVICE_METODO = "CONSUMER_SERVICE ::: Entrou no método ";

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private CardServiceImpl cardServiceImpl;

    public Consumer findById(Integer id) {
        log.info(CONSUMER_SERVICE_METODO + "findById");
        Optional<Consumer> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Consumer.class.getSimpleName()));
    }

    public List<Consumer> findAll() {
        log.info(CONSUMER_SERVICE_METODO + "findAll");
        return repository.findAll();
    }

    public Consumer create(Consumer obj) {
        log.info(CONSUMER_SERVICE_METODO + "create");
        verifyIfConsumerAlreadyExists(obj);
        cardServiceImpl.validIfCardNumberAlreadyExists(obj);
        return saveConsumer(obj);
    }

    public Consumer update(Integer id, Consumer obj) {
        log.info(CONSUMER_SERVICE_METODO + "update");
        obj.setId(id);
        return saveConsumer(obj);
    }

    /**
     * Salva consumer no banco
     */
    private Consumer saveConsumer(Consumer obj) {
        log.info(CONSUMER_SERVICE_METODO + "saveConsumer");
        obj = repository.save(obj);
        obj.getContact().setConsumer(obj);
        obj.getAddress().setConsumer(obj);

        for(Card card : obj.getCards()) {
            card.setConsumer(obj);
            cardRepository.save(card);
        }

        contactRepository.save(obj.getContact());
        addressRepository.save(obj.getAddress());
        return obj;
    }

    /**
     * Verifica se já existe um usuário cadastrado no sistema
     * com o mesmo numero de documento informado na requisição
     */
    private void verifyIfConsumerAlreadyExists(Consumer obj) {
        log.info(CONSUMER_SERVICE_METODO + "verifyIfConsumerAlreadyExists");
        Optional<Consumer> consumer = repository.findUserByDocumentNumber(obj.getDocumentNumber());
        if(consumer.isPresent()) {
            throw new DataIntegrityViolationException("Documento já cadastrado no sistema");
        }
    }

}
