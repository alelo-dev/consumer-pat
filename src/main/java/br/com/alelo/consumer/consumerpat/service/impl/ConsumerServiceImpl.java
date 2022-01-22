package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.controller.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.in.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Phone;
import br.com.alelo.consumer.consumerpat.exceptions.CardNumberException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerDocumentException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    final private ConsumerRepository consumerRepository;
    final private CardRepository cardRepository;

    @Override
    public Consumer createConsumer(Consumer consumer) {
        consumerRepository.findByDocumentNumber(consumer.getDocumentNumber()).ifPresent(
                consumerPresent -> {
                    throw new ConsumerDocumentException();
                });

        consumer.getCardList().forEach(card -> {
            cardRepository.findCardsByNumber(card.getNumber()).ifPresent(
                    cardPresent -> {
                        throw new CardNumberException();
                    });
        });

        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer updateConsumer(Integer id, UpdateConsumerDTO updateConsumerDTO) {

        Optional<Consumer> consumer = consumerRepository.findById(id);
        if (consumer.isPresent()) {
            Consumer consumerEntity = consumer.get();
            List<Phone> phoneList = new ArrayList<>();

            updateConsumerDTO.getUpdatePhoneDTOS().forEach(updatePhoneDTO -> {
                phoneList.add(Phone.builder()
                        .id(null)
                        .phoneType(updatePhoneDTO.getPhoneType().getValue())
                        .number(updatePhoneDTO.getNumber())
                        .createdAt(LocalDateTime.now())
                        .build());

            });

            System.out.println("SIZE "+phoneList.size());
            return consumerRepository.save(Consumer.builder()
                    .id(consumerEntity.getId())
                    .createdAt(consumerEntity.getCreatedAt())
                    .name(updateConsumerDTO.getName())
                    .birthDate(updateConsumerDTO.getBirthDate())
                    .city(updateConsumerDTO.getCity())
                    .country(updateConsumerDTO.getCountry())
                    .documentNumber(updateConsumerDTO.getDocumentNumber())
                    .number(updateConsumerDTO.getNumber())
                    .postalCode(updateConsumerDTO.getPostalCode())
                    .email(updateConsumerDTO.getEmail())
                    .street(updateConsumerDTO.getStreet())
                    .updatedAt(LocalDateTime.now())
                    .phoneList(phoneList)
                    .build());
        } else {
            throw new ConsumerNotFoundException();
        }
    }

    @Override
    public Page<ResponseConsumerDTO> getPageConsumer(Pageable pageable) {

        return consumerRepository.findAll(pageable).map(ConsumerConverter::fromEntity);
    }
}
