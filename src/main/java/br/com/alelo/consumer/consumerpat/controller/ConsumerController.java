package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping(value = "/consumerList")
    public Page<Consumer> listAllConsumers(@PageableDefault(sort = "id", direction = Sort.Direction.DESC , page = 0, size = 50) Pageable pagination) {
        return consumerService.getAllConsumersList(pagination);
    }

    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Object> createConsumer(@RequestBody Consumer consumer) {

        Consumer consumerCreated = consumerService.createNewConsumer(consumer);
        return ResponseEntity.ok().body(consumerCreated);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/updateConsumer")
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "/updateBalance")
    public void updateBalance(Long cardNumber, double ammount) {
        consumerService.updateBalance(cardNumber, ammount);
    }

    @PostMapping(value = "/registerSale")
    public void registerSale(String establishmentName, Long cardNumber, String productDescription, double value) {
        consumerService.registerSale(establishmentName, cardNumber, productDescription, value);
    }

}
