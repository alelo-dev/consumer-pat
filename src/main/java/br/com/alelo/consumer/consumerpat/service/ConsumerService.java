package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {

    Page<ConsumerResponse> findAllConsumers(Pageable page);

    ConsumerResponse save(ConsumerRequest consumerRequest);

    ConsumerResponse update(Integer id, ConsumerRequest consumerRequest);
}
