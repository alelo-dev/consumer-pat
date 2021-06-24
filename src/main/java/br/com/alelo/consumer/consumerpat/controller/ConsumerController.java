package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
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
public class ConsumerController {

    private ConsumerService consumerService;

    @GetMapping
    @ApiOperation("Listar todos os consumidores de modo paginado")
    public ResponseEntity<Page<Consumer>> getConsumers(Pageable pageable) {
        Page<Consumer> consumers = consumerService.findAll(pageable);
        return consumers.isEmpty() ? noContent().build() : ok(consumers);
    }


    @PostMapping
    @ApiOperation("Cadastrar um novo consumidor")
    public ResponseEntity<Consumer> postConsumer(@RequestBody Consumer consumer) {
        consumerService.save(consumer);
        return created(null).body(consumer);
    }

    @PatchMapping("/{consumerId}")
    @ApiOperation("Alterar dados de um consumidor sem alterar o saldo do(s) cartão(ões)")
    public ResponseEntity<Consumer> patchConsumer(@PathVariable @ApiParam("Id do consumidor") int consumerId, @RequestBody Consumer consumer) {
        consumer.setId(consumerId);
        consumerService.save(consumer);
        return ok(consumer);
    }

    @PatchMapping("/{consumerId}/balance/{cardNumber}/{transactionAmount}")
    @ApiOperation("Recarga de cartão")
    public ResponseEntity<Void> patchCardBalance(
            @PathVariable @ApiParam("Id do consumidor") int consumerId,
            @PathVariable @ApiParam("Número do cartão") String cardNumber,
            @PathVariable @ApiParam("Valor da carga. Deve ser maior que zero")
                    BigDecimal transactionAmount) {
        consumerService.cardRecharge(consumerId, cardNumber, transactionAmount);
        return ok().build();
    }
}
