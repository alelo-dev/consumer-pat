package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Address;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.entity.Contact;
import br.com.alelo.consumer.consumerpat.model.enums.ContactType;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.model.repository.AddressRepository;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ContactRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static br.com.alelo.consumer.consumerpat.utils.BigDecimalUtils.isGreaterThanZero;
import static br.com.alelo.consumer.consumerpat.utils.StringUtils.isNotEmpty;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerRepository consumerRepository;
    private AddressRepository addressRepository;
    private ContactRepository contactRepository;
    private CardRepository cardRepository;

    @Override
    public Consumer findById(Long consumerId) {
        return consumerRepository.findById(consumerId)
                .orElseThrow(() -> new ResourceNotFoundException("Consumer not found!"));
    }

    @Override
    public Page<Consumer> findAll(ConsumerFilterVO filters, Pageable pageable) {
        return consumerRepository.findAllByFilters(filters, pageable);
    }

    @Override
    @Transactional
    public Consumer save(NewConsumerFormVO form) {
        checkIfConsumerAlreadyExists(form.getDocumentNumber());

        Consumer consumer = buildConsumer(form);

        addressRepository.save(consumer.getAddress());
        consumerRepository.save(consumer);
        contactRepository.saveAll(consumer.getContacts());
        cardRepository.saveAll(consumer.getCards());

        return consumer;
    }

    @Override
    @Transactional
    public Consumer update(Long consumerId, UpdateConsumerFormVO form) {
        Consumer consumer = consumerRepository.findById(consumerId)
            .orElseThrow(() -> new ResourceNotFoundException("Consumer not found!"));

        mergeConsumerData(form, consumer);

        return consumerRepository.save(consumer);
    }

    private void mergeConsumerData(UpdateConsumerFormVO form, Consumer consumer) {
        if (isNotEmpty(form.getName())) {
            consumer.setName(form.getName());
        }
        if (isNotEmpty(form.getDocumentNumber())) {
            consumer.setDocumentNumber(form.getDocumentNumber());
        }
        if (form.getBirthDate() != null) {
            consumer.setBirthDate(form.getBirthDate());
        }
    }

    private void checkIfConsumerAlreadyExists(String documentNumber) {
        Optional<Consumer> consumerOpt = consumerRepository.findByDocumentNumber(documentNumber);
        if (consumerOpt.isPresent()) {
            throw new BusinessException("There is already a consumer with the informed document (CPF)!");
        }
    }

    private Consumer buildConsumer(NewConsumerFormVO form) {
        Consumer consumer = new Consumer();
        consumer.setName(form.getName());
        consumer.setDocumentNumber(form.getDocumentNumber());
        consumer.setBirthDate(form.getBirthDate());
        consumer.setAddress(buildAddress(form));
        consumer.setContacts(buildContacts(form, consumer));
        consumer.setCards(buildCards(form, consumer));
        return consumer;
    }

    private Address buildAddress(NewConsumerFormVO form) {
        return new Address(null, form.getStreet(), form.getNumber(), form.getCity(), form.getCountry(), form.getPostalCode());
    }

    private Set<Contact> buildContacts(NewConsumerFormVO form, Consumer consumer) {
        Set<Contact> contacts = new HashSet<>();
        if (isNotEmpty(form.getPhone())) {
            contacts.add(buildContact(form.getPhone(), ContactType.PHONE, consumer));
        }
        if (isNotEmpty(form.getMobilePhone())) {
            contacts.add(buildContact(form.getMobilePhone(), ContactType.MOBILE_PHONE, consumer));
        }
        if (isNotEmpty(form.getResidencePhone())) {
            contacts.add(buildContact(form.getResidencePhone(), ContactType.RESIDENCE_PHONE, consumer));
        }
        if (isNotEmpty(form.getEmail())) {
            contacts.add(buildContact(form.getEmail(), ContactType.EMAIL, consumer));
        }
        return contacts;
    }

    private Set<Card> buildCards(NewConsumerFormVO form, Consumer consumer) {
        Set<Card> cards = new HashSet<>();
        if (isGreaterThanZero(form.getFoodCardBalance())) {
            cards.add(buildCard(form.getFoodCardBalance(), EstablishmentType.FOOD, consumer));
        }
        if (isGreaterThanZero(form.getDrugstoreCardBalance())) {
            cards.add(buildCard(form.getDrugstoreCardBalance(), EstablishmentType.DRUGSTORE, consumer));
        }
        if (isGreaterThanZero(form.getFuelCardBalance())) {
            cards.add(buildCard(form.getFuelCardBalance(), EstablishmentType.FUEL, consumer));
        }
        return cards;
    }

    private Contact buildContact(String value, ContactType type, Consumer consumer) {
        return new Contact(null, value, type, consumer);
    }

    private Card buildCard(BigDecimal balance, EstablishmentType type, Consumer consumer) {
        return new Card(null, Math.abs(new Random().nextLong()), balance, type, consumer);
    }

}
