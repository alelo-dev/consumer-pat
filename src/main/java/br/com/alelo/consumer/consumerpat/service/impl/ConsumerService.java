package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ConsumerService {

    private final ConsumerRepository repository;
    private final CardRepository cardRepository;

    public List<ConsumerDTO> getAllConsumersList() {
        return repository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumerDTO save(ConsumerDTO dto) {
        var consumer = Consumer
                .builder()
                .id(dto.getId())
                .birthDate(dto.getBirthDate())
                .documentNumber(dto.getDocumentNumber())
                .name(dto.getName())
                .contact(Contact
                        .builder()
                        .email(dto.getEmail())
                        .mobilePhoneNumber(dto.getMobilePhoneNumber())
                        .phoneNumber(dto.getPhoneNumber())
                        .residencePhoneNumber(dto.getResidencePhoneNumber())
                        .build())
                .address(Address
                        .builder()
                        .city(dto.getCity())
                        .country(dto.getCountry())
                        .number(dto.getNumber())
                        .postalCode(dto.getPostalCode())
                        .street(dto.getStreet())
                        .build())
                .build();
        repository.save(consumer);
        addFoodCard(dto, consumer);
        addDrugstoreCard(dto, consumer);
        addFuelCard(dto, consumer);
        return entityToDto(consumer);
    }

    private void addFoodCard(ConsumerDTO dto, Consumer consumer) {
        Optional<Card> cardOp = cardRepository.findCardByCardNumber(dto.getFoodCardNumber());
        if(!cardOp.isPresent() && dto.getFoodCardBalance() != null && dto.getFoodCardNumber() != null) {
            cardRepository.save(Card
                    .builder()
                    .establishmentType(EstablishmentType.FOOD)
                    .cardBalance(dto.getFoodCardBalance())
                    .cardNumber(dto.getFoodCardNumber())
                    .consumer(consumer)
                    .build());
        }
    }

    private void addDrugstoreCard(ConsumerDTO dto, Consumer consumer) {
        Optional<Card> cardOp = cardRepository.findCardByCardNumber(dto.getDrugstoreCardNumber());
        if(!cardOp.isPresent() && dto.getDrugstoreCardBalance()!= null && dto.getDrugstoreCardNumber() != null) {
            cardRepository.save(Card
                    .builder()
                    .establishmentType(EstablishmentType.DRUGSTORE)
                    .cardBalance(dto.getDrugstoreCardBalance())
                    .cardNumber(dto.getDrugstoreCardNumber())
                    .consumer(consumer)
                    .build());
        }
    }

    private void addFuelCard(ConsumerDTO dto, Consumer consumer) {
        Optional<Card> cardOp = cardRepository.findCardByCardNumber(dto.getFuelCardNumber());
        if(!cardOp.isPresent() && dto.getFuelCardBalance() != null && dto.getFuelCardNumber() != null) {
            cardRepository.save(Card
                    .builder()
                    .establishmentType(EstablishmentType.FUEL)
                    .cardBalance(dto.getFuelCardBalance())
                    .cardNumber(dto.getFuelCardNumber())
                    .consumer(consumer)
                    .build());
        }
    }

    private ConsumerDTO entityToDto(Consumer consumer) {
        ConsumerDTO dto = ConsumerDTO
                .builder()
                .id(consumer.getId())
                .birthDate(consumer.getBirthDate())
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .build();
        if(Objects.nonNull(consumer.getContact())) {
            dto.setMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber());
            dto.setResidencePhoneNumber(consumer.getContact().getResidencePhoneNumber());
            dto.setPhoneNumber(consumer.getContact().getPhoneNumber());
            dto.setEmail(consumer.getContact().getEmail());
        }
        if(Objects.nonNull(consumer.getAddress())) {
            dto.setStreet(consumer.getAddress().getStreet());
            dto.setNumber(consumer.getAddress().getNumber());
            dto.setCity(consumer.getAddress().getCity());
            dto.setCountry(consumer.getAddress().getCountry());
            dto.setPostalCode(consumer.getAddress().getPostalCode());
        }
        if(Objects.nonNull(consumer.getCards())) {
            consumer.getCards().forEach(card -> {
                if(card.getEstablishmentType().equals(EstablishmentType.FOOD)) {
                    dto.setFoodCardNumber(card.getCardNumber());
                    dto.setFoodCardBalance(card.getCardBalance());
                } else if(card.getEstablishmentType().equals(EstablishmentType.DRUGSTORE)) {
                    dto.setDrugstoreCardNumber(card.getCardNumber());
                    dto.setDrugstoreCardBalance(card.getCardBalance());
                } else if(card.getEstablishmentType().equals(EstablishmentType.FUEL)) {
                    dto.setFuelCardNumber(card.getCardNumber());
                    dto.setFuelCardBalance(card.getCardBalance());
                }
            });
        }
        return dto;
    }

}
