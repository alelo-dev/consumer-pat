package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.contracts.ConsumerControllerContract;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerCreateDto;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/consumer")
public class ConsumerController implements ConsumerControllerContract {

    @Autowired
    private ConsumerService consumerService;

    @Override
    public ResponseEntity<Iterable<Consumer>> listAllConsumers(
            @PageableDefault(page = 0, value = 500, size = 500, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok().body(consumerService.getAllConsumersPaginate(pageable));
    }

    @Override
    public ResponseEntity<Consumer> createConsumer(ConsumerCreateDto consumerCreateDto) {

        Consumer consumer = consumerService.registerConsumer(consumerCreateDto.toDomainObject());

        URI uriResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consumer.getId())
                .toUri();

        return ResponseEntity.created(uriResource).build();
    }

    @Override
    public ResponseEntity<Void> updateConsumer(Long consumerId, ConsumerUpdateDto consumerUpdateDto) {
        consumerService.updateConsumer(consumerId, consumerUpdateDto.toDomainObject());
        return ResponseEntity.ok().build();
    }
}
