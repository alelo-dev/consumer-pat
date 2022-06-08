package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/consumer")
@AllArgsConstructor
public class ConsumerController {

    private final ConsumerService service;
    private final CardService cardService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public ResponseEntity<Page<Consumer>> listAllConsumers(Pageable pageable) {
        return ResponseEntity.ok(service.consumerList(pageable));
    }

    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) throws RuntimeException, Exception {
        return ResponseEntity.ok(service.createConsumer(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping(value = "/updateConsumer")
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(service.updateConsumer(consumer));
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "/setcardbalance")
    public ResponseEntity<String> setBalance(@RequestParam String cardNumber, @RequestParam double value) {
        cardService.setCardbalance(cardNumber, value);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "/addconsumercard")
    public ResponseEntity<Card> addConsumerCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.addConsumerCard(card));
    }

    @DeleteMapping(value = "/removeconsumercard/{id}")
    public ResponseEntity<String> removeConsumerCard(@PathVariable int id) {
        try {
            cardService.removeConsumerCard(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping(value = "/buy")
    public ResponseEntity<String> buy(@RequestParam int establishmentType, @RequestParam String establishmentName,
            @RequestParam String cardNumber, @RequestParam String productDescription,
            @RequestParam double value) throws Exception {
        try {

            service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
