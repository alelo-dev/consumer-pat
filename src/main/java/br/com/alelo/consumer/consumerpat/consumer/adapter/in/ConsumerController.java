package br.com.alelo.consumer.consumerpat.consumer.adapter.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.ListConsumersCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.ListConsumersUseCase;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.NewConsumer;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.RegisterConsumerCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.RegisterConsumerUseCase;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumer;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Tag(name = "Consumers", description = "Responsible for consumers management.")
@Log4j2
@RestController
@RequestMapping(value = "/consumers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ConsumerController {

    private final RegisterConsumerUseCase registerConsumerUseCase;
    private final ListConsumersUseCase listConsumersUseCase;
    private final UpdateConsumerUseCase updateConsumerUseCase;

    @Operation(summary = "Lists all consumers.")
    @GetMapping
    public Page<ConsumerApiModel> listAllConsumers(@ParameterObject Pageable pageable) {

        var command = ListConsumersCommand.of(pageable);
        return listConsumersUseCase.listAll(command);
    }

    @Operation(summary = "Registers a new consumer.")
    @PostMapping
    public ResponseEntity<ConsumerApiModel> createConsumer(@Valid @RequestBody NewConsumer newConsumer,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        var command = RegisterConsumerCommand.of(newConsumer);
        var savedConsumer = registerConsumerUseCase.registerConsumer(command);
        var locationUri = uriComponentsBuilder.path("/consumers/{id}")
                .buildAndExpand(savedConsumer.getConsumerId()).toUri();

        return ResponseEntity.created(locationUri)
                .body(savedConsumer);
    }

    @Operation(summary = "Updates an existing consumer.")
    @PutMapping(value = "/{consumerId}")
    public ResponseEntity<ConsumerApiModel> updateConsumer(@PathVariable Integer consumerId,
                                                           @Valid @RequestBody UpdateConsumer updatingConsumer) {

        var command = UpdateConsumerCommand.of(consumerId, updatingConsumer);
        var updatedConsumer = updateConsumerUseCase.updateConsumer(command);
        return ResponseEntity.ok(updatedConsumer);
    }
}
