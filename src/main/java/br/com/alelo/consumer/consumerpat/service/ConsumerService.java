package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.enums.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final ExtractService extractService;
    private final CardService cardService;

    public Page<Consumer> findConsumersPageable(Pageable pageable) {
        return consumerRepository.findAll(pageable);
    }

    public Optional<Consumer> getById(Long id) {
        return consumerRepository.findById(id);
    }

    public Consumer createConsumer(ConsumerDTO consumerDTO) {
        return consumerRepository.save(toEntity(consumerDTO));
    }

    public Consumer updateConsumer(Consumer consumer, ConsumerUpdateDTO consumerDTO) {

        if (consumerDTO.getName() != null)
            consumer.setName(consumerDTO.getName());
        if (consumerDTO.getDocumentNumber() != null)
            consumer.setDocumentNumber(consumerDTO.getDocumentNumber());
        if (consumerDTO.getBirthDate() != null)
            consumer.setBirthDate(consumerDTO.getBirthDate());

        // atualizar contato e endereço de acordo com regras de atualização para duas entidades
        // não permitir atualização do balanço e número do cartão
        // depender do requisito, tratamento pode diferenciar caso consumidor possa ter mais de um cartyão do mesmo tipo

        return consumerRepository.save(consumer);
    }

    public Card creditBalance(Long cardNumber, Double value) {
        Card card = cardService.getCardByNumber(cardNumber);

        if (card != null) {
            card.setBalance(card.getBalance() + value);
            return cardService.save(card);
        }

        return null;
    }

    public Extract buy(Integer establishmentType, String establishmentName, Long cardNumber, String productDescription, Double value) {
        Card card = cardService.getCardByNumber(cardNumber);

        // Para compras no cartão de alimentação o cliente recebe um desconto de 10% e cartão de combustivel existe um acrescimo de 35%
        if (establishmentType == 1 && card.getType().equals(CardTypeEnum.FOOD))
            value -= value * 0.1;
        else if (establishmentType == 3 && card.getType().equals(CardTypeEnum.FUEL))
            value += value * 0.35;

        card.setBalance(card.getBalance() - value);
        return extractService.save(establishmentName, productDescription, card, value);
    }

    public Consumer toEntity(ConsumerDTO consumerDTO) {

        Contact contact = Contact.builder()
                .phoneNumber(consumerDTO.getPhoneNumber())
                .mobilePhoneNumber(consumerDTO.getMobilePhoneNumber())
                .residencePhoneNumber(consumerDTO.getPhoneNumber())
                .email(consumerDTO.getEmail())
                .build();

        Address address = Address.builder()
                .number(consumerDTO.getNumber())
                .city(consumerDTO.getCity())
                .country(consumerDTO.getCountry())
                .portalCode(consumerDTO.getPortalCode())
                .build();

        Card foodCard = Card.builder()
                .number(consumerDTO.getFoodCardNumber())
                .balance(consumerDTO.getFoodCardBalance())
                .type(CardTypeEnum.FOOD)
                .build();

        Card fuelCard = Card.builder()
                .number(consumerDTO.getFuelCardNumber())
                .balance(consumerDTO.getFuelCardBalance())
                .type(CardTypeEnum.FUEL)
                .build();

        Card drugstoreCard = Card.builder()
                .number(consumerDTO.getDrugstoreCardNumber())
                .balance(consumerDTO.getDrugstoreCardBalance())
                .type(CardTypeEnum.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .name(consumerDTO.getName())
                .documentNumber(consumerDTO.getDocumentNumber())
                .birthDate(consumerDTO.getBirthDate())
                .contact(contact)
                .address(address)
                .cards(Arrays.asList(foodCard, fuelCard, drugstoreCard))
                .build();

        return consumer;
    }

}
