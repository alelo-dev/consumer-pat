package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public Page<Consumer> listAllConsumers(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable page) {
        return service.listAllConsumers(page);
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        return service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("{/id}")
    public Consumer updateConsumer(@RequestBody Consumer consumer, @PathVariable Long id) {
        return service.updateConsumer(consumer, id);
    }

}
