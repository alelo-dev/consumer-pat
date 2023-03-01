package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardType;
import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.domain.Address;
import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import br.com.alelo.consumer.consumerpat.consumer.domain.Contact;
import br.com.alelo.consumer.consumerpat.consumer.domain.ContactType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConsumerConverter {

    public ConsumerApiModel toApiModel(Consumer consumer) {

        var apiModel = new ConsumerApiModel();

        apiModel.setConsumerId(consumer.getConsumerId());
        apiModel.setDocumentNumber(consumer.getDocumentNumber());
        apiModel.setName(consumer.getName());
        apiModel.setBirthDate(consumer.getBirthDate());

        return apiModel;
    }

    public Consumer updateEntity(Consumer consumer, UpdateConsumer updateConsumer) {

        consumer.setName(updateConsumer.getName());
        consumer.setDocumentNumber(updateConsumer.getDocumentNumber());
        consumer.setBirthDate(updateConsumer.getBirthDate());

        return consumer;
    }

    public Consumer toEntity(RegisterConsumerCommand command) {

        var consumer = new Consumer();

        consumer.setName(command.getName());
        consumer.setBirthDate(command.getBirthDate());
        consumer.setDocumentNumber(command.getDocumentNumber());

        var contacts = command.getContacts()
                .stream()
                .map(newContact -> toContact(newContact, consumer))
                .collect(Collectors.toSet());

        var cards = command.getCards()
                .stream()
                .map(newCard -> toCard(newCard, consumer))
                .collect(Collectors.toSet());

        var addresses = command.getAddresses()
                .stream()
                .map(newAddress -> toAddress(newAddress, consumer))
                .collect(Collectors.toSet());

        consumer.setContacts(contacts);
        consumer.setCards(cards);
        consumer.setAddresses(addresses);

        return consumer;
    }

    private Address toAddress(NewAddress newAddress, Consumer consumer) {

        var address = new Address();

        address.setStreetName(newAddress.getStreetName());
        address.setNumber(newAddress.getNumber());
        address.setCity(newAddress.getCity());
        address.setCountry(newAddress.getCountry());
        address.setPostalCode(newAddress.getPostalCode());
        address.setConsumer(consumer);

        return address;
    }

    private Card toCard(NewCard newCard, Consumer consumer) {

        var cardType = new CardType();
        cardType.setCardTypeId(newCard.getType());

        var card = new Card();
        card.setType(cardType);
        card.setNumber(newCard.getNumber());
        card.setHolder(consumer);

        return card;
    }

    private Contact toContact(NewContact newContact, Consumer consumer) {

        var contactType = new ContactType();
        contactType.setContactTypeId(newContact.getType());

        var contact = new Contact();
        contact.setType(contactType);
        contact.setInfo(newContact.getInfo());
        contact.setConsumer(consumer);

        return contact;
    }
}
