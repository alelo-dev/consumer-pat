package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import java.util.ArrayList;
import java.util.Objects;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.AddressDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.CardDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ContactDTO;
import br.com.alelo.consumer.consumerpat.domain.Address;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.domain.Contact;

public class ConsumerDTOMapper {

  public static Consumer mapToDomain(ConsumerDTO consumerDTO) {

    var consumerBuilder = Consumer.builder().name(consumerDTO.getName())
        .documentNumber(consumerDTO.getDocumentNumber()).birthDate(consumerDTO.getBirthDate());


    if (Objects.nonNull(consumerDTO.getContact())) {

      var contact =
          Contact.builder().mobilePhoneNumber(consumerDTO.getContact().getMobilePhoneNumber())
              .residencePhoneNumber(consumerDTO.getContact().getResidencePhoneNumber())
              .phoneNumber(consumerDTO.getContact().getPhoneNumber())
              .email(consumerDTO.getContact().getEmail()).build();
      consumerBuilder.contact(contact);

    }

    if (Objects.nonNull(consumerDTO.getAddress())) {
      var address = Address.builder().street(consumerDTO.getAddress().getStreet())
          .number(consumerDTO.getAddress().getNumber()).city(consumerDTO.getAddress().getCity())
          .country(consumerDTO.getAddress().getCountry())
          .portalCode(consumerDTO.getAddress().getPortalCode()).build();
      consumerBuilder.address(address);
    }

    var cards = new ArrayList<Card>();
    consumerDTO.getCards().forEach(cardDTO -> {

      var cardBuilder = Card.builder().number(cardDTO.getNumber()).balance(cardDTO.getBalance());

      if (Objects.nonNull(cardDTO.getType())) {
        cardBuilder.type(CardType.valueOf(cardDTO.getType().toUpperCase()));
      }

      cards.add(cardBuilder.build());

    });

    return consumerBuilder.cards(cards).build();
  }

  public static ConsumerDTO mapToDTO(Consumer consumer) {

    var consumerDTOBuilder = ConsumerDTO.builder().name(consumer.getName())
        .documentNumber(consumer.getDocumentNumber()).birthDate(consumer.getBirthDate());


    if (Objects.nonNull(consumer.getContact())) {

      var contactDTO =
          ContactDTO.builder().mobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())
              .residencePhoneNumber(consumer.getContact().getResidencePhoneNumber())
              .phoneNumber(consumer.getContact().getPhoneNumber())
              .email(consumer.getContact().getEmail()).build();
      consumerDTOBuilder.contact(contactDTO);

    }

    if (Objects.nonNull(consumer.getAddress())) {
      var addressDTO = AddressDTO.builder().street(consumer.getAddress().getStreet())
          .number(consumer.getAddress().getNumber()).city(consumer.getAddress().getCity())
          .country(consumer.getAddress().getCountry())
          .portalCode(consumer.getAddress().getPortalCode()).build();
      consumerDTOBuilder.address(addressDTO);
    }

    var cardDTOs = new ArrayList<CardDTO>();
    consumer.getCards().forEach(card -> {

      var cardDTO = CardDTO.builder().number(card.getNumber()).balance(card.getBalance())
          .type(card.getType().toString()).build();

      cardDTOs.add(cardDTO);

    });

    return consumerDTOBuilder.cards(cardDTOs).build();
  }

}
