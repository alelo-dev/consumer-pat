package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private CardService cardService;

    /**
     * Busca Consumer por id
     */
    public Consumer findById(Integer id) {
        Optional<Consumer> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Consumer.class.getSimpleName()));
    }

    /**
     * Listar Consumers
     */
    public List<Consumer> findAll() {
        return repository.findAll();
    }

    /**
     * Criar novo consumer
     */
    public Consumer create(Consumer obj) {
        verifyIfConsumerAlreadyExists(obj);
        cardService.validIfCardNumberAlreadyExists(obj);
        return saveConsumer(obj);
    }

    /**
     * Atualiza Consumer
     */
    public Consumer update(Integer id, Consumer obj) {
        obj.setId(id);
        return saveConsumer(obj);
    }

    /**
     * Salva consumer no banco
     */
    private Consumer saveConsumer(Consumer obj) {
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
        Optional<Consumer> consumer = repository.findUserByDocumentNumber(obj.getDocumentNumber());
        if(consumer.isPresent()) {
            throw new DataIntegrityViolationException("Documento já cadastrado no sistema");
        }
    }

}
