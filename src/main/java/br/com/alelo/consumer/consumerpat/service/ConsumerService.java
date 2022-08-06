package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {

    Page<ConsumerResponse> listAllConsumers(Pageable pageable);

    ConsumerResponse createConsumer(ConsumerRequest consumerRequest);

    ConsumerResponse updateConsumer(Long id, ConsumerRequest consumerRequest);

    ConsumerResponse findCardsForConsumer(String documentNumber);
}
