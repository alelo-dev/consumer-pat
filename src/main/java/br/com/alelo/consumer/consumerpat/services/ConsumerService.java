package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerRequestToConsumerConverter;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerToConsumerResponseConverter;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.unprocessableEntity.ConsumerUnprocessableEntityException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;
    private final CardService cardService;
    private final ConsumerToConsumerResponseConverter toConsumerResponse;
    private final ConsumerRequestToConsumerConverter toConsumer;


    public Page<ConsumerResponse> getAllConsumersList(final Pageable pageable) {
        final Page<Consumer> consumers = repository.getAllConsumersList(pageable);
        final List<ConsumerResponse> collect = consumers
                .stream()
                .map(toConsumerResponse::convert)
                .collect(toList());
        return new PageImpl<>(collect, pageable, consumers.getTotalElements());

    }

    public ConsumerResponse create(final ConsumerRequest request) {
        final Consumer consumer = toConsumer.convert(request);
        try {
            final Consumer consumerSaved = repository.save(consumer);
            cardService.save(request.getCards(), consumerSaved.getId());
            return toConsumerResponse.convert(consumerSaved);
        } catch (DataIntegrityViolationException e) {
            log.error("Consumer já possui um CPF: [ {} ] cadastrado", request.getDocumentNumber());
            throw new ConsumerUnprocessableEntityException(format("Consumer já possui um documento: [ %S ] cadastrado", request.getDocumentNumber()));
        }
    }

    public void update(ConsumerRequest request, long consumerId) {
        repository
                .findById(consumerId)
                .ifPresentOrElse(consumer -> updateConsumer(request, consumer), () -> log.info("consumer não encontrado"));
    }

    private void updateConsumer(final ConsumerRequest request, final Consumer consumer) {
        consumer.update(request);
        repository.save(consumer);
    }
}
