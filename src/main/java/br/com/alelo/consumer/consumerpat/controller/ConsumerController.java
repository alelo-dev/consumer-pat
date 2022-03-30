package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;

    private final CardService cardService;

    /* Deve listar todos os clientes (cerca de 500) */

    @GetMapping("/consumerList")
    public ResponseEntity<Object> listAllConsumers() {
        List<Consumer> consumers = consumerService.listAllConsumers();
        return new ResponseEntity<>(consumers, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity createConsumer(@RequestBody Consumer consumer) {
        consumerService.createConsumer(consumer);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer")
    public ResponseEntity updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
        return new ResponseEntity(HttpStatus.OK);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping("/setcardbalance")
    public ResponseEntity setBalance(Long cardNumber, BigDecimal value) {
        try {
            cardService.setBalance(cardNumber, value);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CardException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/buy")
    public ResponseEntity buy(Long establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {
        try {
            cardService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
