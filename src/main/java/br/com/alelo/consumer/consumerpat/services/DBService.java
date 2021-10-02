package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Classe criada para iniciar uma carga no banco sempre que
 * projeto estiver compilando. Algumas instancias serão criadas
 * e persistidas no banco de dados h2
 */
@Service
public class DBService {

    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void startDB() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Consumer consumer = new Consumer(null, "Valdir", "09129161923", sdf.parse("12/02/1993"), null, null, null);
        Contact contact = new Contact(null, "5543984634308", "3333-3333", "5543984634308", "valdircezar312@gmail.com", consumer);
        Card card = new Card(null, "0000000000000000", 500.5, CardType.FOOD, consumer);
        Address address = new Address(null, "Rua dos testes", "123", "Londrina", "Brasil", "86085530", consumer);

        consumer.setContact(contact);
        consumer.setCards(List.of(card));
        consumer.setAddress(address);

        consumerRepository.saveAll(List.of(consumer));
        contactRepository.saveAll(List.of(contact));
        cardRepository.saveAll(List.of(card));
        addressRepository.saveAll(List.of(address));
    }
}
