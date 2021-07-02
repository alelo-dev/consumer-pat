package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerWrapp {

    private final ConsumerService consumerService;

    public Page<ConsumerResponse> findAll(final Pageable pageable){
        return consumerService.getAllConsumersList(pageable);
    }

    public void create(final ConsumerRequest request) {
        consumerService.create(request);
    }
}
