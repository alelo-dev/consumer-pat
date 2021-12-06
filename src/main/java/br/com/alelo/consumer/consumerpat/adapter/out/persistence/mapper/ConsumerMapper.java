package br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.domain.Address;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.domain.Contact;

public class ConsumerMapper {

  public static ConsumerEntity mapToJpaEntity(final Consumer consumer) {


    var consumerEntity = new ConsumerEntity();

    consumerEntity.setId(consumer.getId());
    consumerEntity.setName(consumer.getName());
    consumerEntity.setDocumentNumber(consumer.getDocumentNumber());
    consumerEntity.setBirthDate(consumer.getBirthDate());


    if (Objects.nonNull(consumer.getContact())) {
      consumerEntity.setMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber());
      consumerEntity.setResidencePhoneNumber(consumer.getContact().getResidencePhoneNumber());
      consumerEntity.setPhoneNumber(consumer.getContact().getPhoneNumber());
      consumerEntity.setEmail(consumer.getContact().getEmail());
    }

    if (Objects.nonNull(consumer.getAddress())) {
      consumerEntity.setStreet(consumer.getAddress().getStreet());
      consumerEntity.setNumber(consumer.getAddress().getNumber());
      consumerEntity.setCity(consumer.getAddress().getCity());
      consumerEntity.setCountry(consumer.getAddress().getCountry());
      consumerEntity.setPortalCode(consumer.getAddress().getPortalCode());
    }

    Optional.ofNullable(consumer.getCards()).ifPresent(list -> list.forEach(card -> {

      var cardEntity = new CardEntity();
      cardEntity.setNumber(card.getNumber());
      cardEntity.setBalance(card.getBalance());
      cardEntity.setType(card.getType().toString());
      consumerEntity.addCard(cardEntity);

    }));

    return consumerEntity;

  }

  public static Consumer mapToDomain(final ConsumerEntity consumerEntity) {

    var consumer = Consumer.builder().id(consumerEntity.getId()).name(consumerEntity.getName())
        .documentNumber(consumerEntity.getDocumentNumber())
        .birthDate(consumerEntity.getBirthDate());

    var contact = Contact.builder().mobilePhoneNumber(consumerEntity.getMobilePhoneNumber())
        .residencePhoneNumber(consumerEntity.getResidencePhoneNumber())
        .phoneNumber(consumerEntity.getPhoneNumber()).email(consumerEntity.getEmail()).build();

    var address = Address.builder().street(consumerEntity.getStreet())
        .number(consumerEntity.getNumber()).city(consumerEntity.getCity())
        .country(consumerEntity.getCountry()).portalCode(consumerEntity.getPortalCode()).build();

    var cards = new ArrayList<Card>();

    Optional.ofNullable(consumerEntity.getCards()).ifPresent(list -> list.forEach(card -> {

      var cardReturned = Card.builder().number(card.getNumber()).balance(card.getBalance())
          .type(CardType.valueOf(card.getType())).build();

      cards.add(cardReturned);

    }));

    consumer.contact(contact).address(address).cards(cards);

    return consumer.build();

  }
  
  public static ConsumerEntity merge(final Consumer consumer, List<CardEntity> cards) {

    var consumerEntity = new ConsumerEntity();
    
    consumerEntity.setId(consumer.getId());
    consumerEntity.setName(consumer.getName());
    consumerEntity.setDocumentNumber(consumer.getDocumentNumber());
    consumerEntity.setBirthDate(consumer.getBirthDate());


    if (Objects.nonNull(consumer.getContact())) {
      consumerEntity.setMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber());
      consumerEntity.setResidencePhoneNumber(consumer.getContact().getResidencePhoneNumber());
      consumerEntity.setPhoneNumber(consumer.getContact().getPhoneNumber());
      consumerEntity.setEmail(consumer.getContact().getEmail());
    }

    if (Objects.nonNull(consumer.getAddress())) {
      consumerEntity.setStreet(consumer.getAddress().getStreet());
      consumerEntity.setNumber(consumer.getAddress().getNumber());
      consumerEntity.setCity(consumer.getAddress().getCity());
      consumerEntity.setCountry(consumer.getAddress().getCountry());
      consumerEntity.setPortalCode(consumer.getAddress().getPortalCode());
    }
    
    consumerEntity.setCards(cards);

    return consumerEntity;
    
  }

}
