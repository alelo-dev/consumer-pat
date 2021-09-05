package br.com.alelo.consumer.pat.controller;

import br.com.alelo.consumer.pat.payload.ConsumerPayload;
import br.com.alelo.consumer.pat.payload.CreateConsumerPayload;
import br.com.alelo.consumer.pat.service.ConsumerService;
import br.com.alelo.consumer.pat.service.RechargeService;
import br.com.alelo.consumer.pat.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;
    private final TransactionService transactionService;
    private final RechargeService rechargeService;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public Page<ConsumerPayload> findAll(@PageableDefault(page = 0, size = 20) @ApiIgnore Pageable pageable) {
        return consumerService.findAllPaginated(pageable);
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public void createConsumer(@RequestBody CreateConsumerPayload consumer) {
        consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/{consumerId}")
    public void updateConsumer(@PathVariable("consumerId") Long consumerId, @RequestBody CreateConsumerPayload consumer) {
        consumerService.updateConsumer(consumerId, consumer);
    }

}
