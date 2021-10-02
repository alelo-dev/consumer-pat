package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Consumer findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Consumer> findAll() {
        return repository.findAll();
    }

    public Consumer create(Consumer obj) {
        return saveConsumer(obj);
    }

    private Consumer saveConsumer(Consumer obj) {
        obj.setId(null);
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
}
