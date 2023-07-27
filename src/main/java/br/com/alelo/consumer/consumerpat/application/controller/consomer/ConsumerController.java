package br.com.alelo.consumer.consumerpat.application.controller.consomer;

import br.com.alelo.consumer.consumerpat.application.controller.consomer.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.controller.consomer.response.ConsumerIdResponse;
import br.com.alelo.consumer.consumerpat.application.controller.consomer.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/consumers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsumerResponse>> listAllConsumers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Consumer> consumers = consumerService.listAll(pageable);
        return new ResponseEntity<>(consumers.map(ConsumerResponse::of), HttpStatus.OK);
    }

    @GetMapping(value = "/{consumerId}")
    public ResponseEntity<ConsumerResponse> findConsumer(@PathVariable UUID consumerId) {
        var consumerFound = consumerService.searchConsumerById(consumerId);
        var response = consumerFound.map(ConsumerResponse::of);
        return response.isPresent() ? ResponseEntity.status(HttpStatus.OK)
                .body(consumerFound.map(ConsumerResponse::of).get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ConsumerResponse());
    }

    @PostMapping
    public ResponseEntity<ConsumerIdResponse> createConsumer(@Valid @RequestBody ConsumerRequest consumerRequest) {
        var consumerId = consumerService.createConsumer(consumerRequest.getConsumer());
        return ResponseEntity.status(HttpStatus.CREATED).body(ConsumerIdResponse.of(consumerId));
    }

    @PutMapping(value = "/{consumerId}")
    public ResponseEntity<Void> updateConsumer(@PathVariable UUID consumerId,
                                               @Valid @RequestBody ConsumerRequest consumerRequest) {
        consumerService.updateConsumer(consumerId, consumerRequest.getConsumer());
        return ResponseEntity.noContent().build();
    }
}
