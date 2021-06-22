package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.*;

@AllArgsConstructor
@RestController
@RequestMapping("consumers")
@Api(tags = "Consumidores", description = "Chamadas para consumidores e seus cartões")
public class ConsumerController {

    private ConsumerService consumerService;

    @GetMapping
    @ApiOperation("Recuperar todos os consumidores (paginado)")
    public ResponseEntity<Page<Consumer>> getConsumers(Pageable pageable) {
        Page<Consumer> consumers = consumerService.findAll(pageable);
        return consumers.isEmpty() ? noContent().build() : ok(consumers);
    }

    @PostMapping
    @ApiOperation("Criar um consumidor")
    public ResponseEntity<Consumer> postConsumer(@RequestBody Consumer newConsumer) {
        consumerService.save(newConsumer);
        return created(null).body(newConsumer);
    }

    @PatchMapping("/{consumerId}")
    @ApiOperation("Atualizar um consumidor (não atualiza os saldos dos cartões). " +
            "Se for informadado dado de um novo cartão, este será criado na API")
    public ResponseEntity<Consumer> patchConsumer(
            @PathVariable @ApiParam("Id do consumidor") int consumerId, @RequestBody Consumer updatedConsumer) {
        updatedConsumer.setId(consumerId);
        consumerService.save(updatedConsumer);
        return ok(updatedConsumer);
    }

    @PatchMapping("/{consumerId}/card-balance/{cardNumber}/{valueToAdd}")
    @ApiOperation("Depositar um valor num cartão")
            public ResponseEntity<Void> patchCardBalance(
            @PathVariable @ApiParam("Id do consumidor") int consumerId,
            @PathVariable @ApiParam("Número do cartão") String cardNumber,
            @PathVariable @ApiParam("Valor a adicionar no cartão. Precisa ser maior ou igual a 0")
                    BigDecimal valueToAdd) {
        consumerService.addValueToCard(consumerId, valueToAdd, cardNumber);
        return ok().build();
    }

}
