package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.City;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.Country;
import br.com.alelo.consumer.consumerpat.respository.CityRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.CountryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    private final CityRepository cityRepository;

    private final ConsumerRepository consumerRepository;

    private final CountryRepository countryRepository;

    public List<ConsumerDTO> listAllConsumers() {
        return consumerRepository.findAll().stream().map(consumer -> {
            List<ContactDTO> contacts = consumer.getContacts().stream().map(contact -> {
                return new ContactDTO(contact.getId(), contact.getContact(), contact.getContactType());
             }).collect(Collectors.toList());

            List<CardDTO> cards = consumer.getCards().stream().map(card -> {
               return new CardDTO(card.getId(), card.getCardNumber(), card.getBalance(), card.getCardType());
            }).collect(Collectors.toList());

            ConsumerDTO consumerDTO = new ConsumerDTO();
            consumerDTO.setId(consumer.getId());
            consumerDTO.setName(consumer.getName());
            consumerDTO.setDocumentNumber(consumer.getDocumentNumber());
            consumerDTO.setBirthDate(consumer.getBirthDate());
            consumerDTO.setContacts(contacts);
            consumerDTO.setCards(cards);

            Address address = consumer.getAddress();
            consumerDTO.setPostalCode(address.getPostalCode());
            consumerDTO.setStreetName(address.getStreetName());
            consumerDTO.setStreetNumber(address.getStreetNumber());
            consumerDTO.setCity(address.getCity().getName());
            consumerDTO.setCountry(address.getCity().getCountry().getName());

            return consumerDTO;
        }).collect(Collectors.toList());
    }

    public City getOrCreateCity(String countryName, String cityName) {
        Country country = countryRepository.findByName(countryName).orElseGet(() -> {
           return countryRepository.save(new Country(null, countryName));
        });

        City city = cityRepository.findByNameAndCountryId(cityName, country.getId()).orElseGet(() -> {
            return cityRepository.save(new City(null, cityName, country));
        });

        return city;
    }

    public Integer createConsumer(CreateConsumerDTO createConsumer) {
        Consumer consumer = new Consumer(createConsumer.getName(), createConsumer.getDocumentNumber(), createConsumer.getBirthDate());

        City city = getOrCreateCity(createConsumer.getCountry(), createConsumer.getCity());
        Address address = new Address(city, createConsumer.getStreetName(), createConsumer.getStreetNumber(), createConsumer.getPostalCode(), consumer);
        consumer.setAddress(address);

        List<Contact> contacts = Stream.ofNullable(createConsumer.getContacts())
                .flatMap(Collection::stream)
                .map(c -> new Contact(c.getContact(), c.getContactType(), consumer))
                .collect(Collectors.toList());
        consumer.setContacts(contacts);

        List<Card> cards = Stream.ofNullable(createConsumer.getCards())
                .flatMap(Collection::stream)
                .map(c -> new Card(c.getCardNumber(), c.getCardType(), c.getBalance(), consumer))
                .collect(Collectors.toList());
        consumer.setCards(cards);

        Consumer savedConsumer = consumerRepository.save(consumer);
        return savedConsumer.getId();
    }

    public void updateConsumer(Integer idConsumer, UpdateConsumerDTO updateConsumer) {
        Consumer consumer = consumerRepository.findById(idConsumer).orElseThrow();
        consumer.setName(updateConsumer.getName());
        consumer.setDocumentNumber(updateConsumer.getDocumentNumber());
        consumer.setBirthDate(updateConsumer.getBirthDate());

        City city = getOrCreateCity(updateConsumer.getCountry(), updateConsumer.getCity());

        Address address = consumer.getAddress();
        address.setCity(city);
        address.setPostalCode(updateConsumer.getPostalCode());
        address.setStreetName(updateConsumer.getStreetName());
        address.setStreetNumber(updateConsumer.getStreetNumber());

        Stream.ofNullable(updateConsumer.getContacts())
            .flatMap(Collection::stream)
            .forEach(updateContact -> {
                consumer.getContacts().stream()
                    .filter(e -> updateContact.getContactType().equals(e.getContactType()))
                    .findFirst()
                    .ifPresentOrElse(contact -> {
                        contact.setContact(updateContact.getContact());
                    }, () -> {
                        Contact contact = new Contact(updateContact.getContact(), updateContact.getContactType(), consumer);
                        consumer.getContacts().add(contact);
                    });
        });

        Stream.ofNullable(updateConsumer.getCards())
            .flatMap(Collection::stream)
            .forEach(updateCard -> {
                consumer.getCards().stream()
                    .filter(e -> updateCard.getCardType().equals(e.getCardType()))
                    .findFirst()
                    .ifPresentOrElse(card -> {
                        card.setCardNumber(updateCard.getCardNumber());
                    }, () -> {
                        Card card = new Card(updateCard.getCardNumber(), updateCard.getCardType(), BigDecimal.ZERO, consumer);
                        consumer.getCards().add(card);
                    });
        });

        consumerRepository.save(consumer);
    }

}
