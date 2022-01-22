package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.controller.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.CardNumberException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerDocumentException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Consumer updateConsumer(Integer id, Consumer consumer) {
        return null;
    }

    @Override
    public Page<ResponseConsumerDTO> getPageConsumer(Pageable pageable) {

        return consumerRepository.findAll(pageable).map(ConsumerConverter::fromEntity);
    }
}
