package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerRequestToConsumerConverter;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerToConsumerResponseConverter;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;
    private final ConsumerToConsumerResponseConverter toConsumerResponse;
    private final ConsumerRequestToConsumerConverter toConsumer;

    public Page<ConsumerResponse> getAllConsumersList(final Pageable pageable) {
        createMOCK();
        final Page<Consumer> consumers = repository.getAllConsumersList(pageable);
        final List<ConsumerResponse> collect = consumers
                .stream()
                .map(toConsumerResponse::convert)
                .collect(toList());
        return new PageImpl<>(collect, pageable, consumers.getTotalElements());

    }

    public void create(final ConsumerRequest request) {
        final Consumer consumer = toConsumer.convert(request);
        try {
            repository.save(consumer);
        }catch (Exception e){
            log.error("Consumer já possui um CPF: [ {} ] cadastrado", request.getDocumentNumber());
        }
    }

    private void createMOCK() {
        for (int i = 1; i < 16; i++) {
            int finalI = i;
            repository.findById(i).ifPresentOrElse(consumer -> log.info("Consumer id: {}", consumer.getId()), () -> createMock(finalI));

        }
    }

    private void createMock(final int i) {
        repository.save(Consumer
                .builder()
                .name("Teste " + i)
                .documentNumber("77533311100-" + i)
                .birthDate(LocalDate.of(1980 + i, 8, 1 + i))
                .build());
    }
}
