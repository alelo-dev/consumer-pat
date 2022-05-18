package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public Page<Consumer> listAllConsumers(Pageable pageable) {
        return service.consumerList(pageable);
    }

    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(service.createConsumer(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping(value = "/updateConsumer")
    public void updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping(value = "/setcardbalance")
    public void setBalance(int cardNumber, double value) {
        service.setCardbalance(cardNumber, value);
    }

    @ResponseBody
    @GetMapping(value = "/buy")
    public ResponseEntity<String> buy(@RequestParam int establishmentType, @RequestParam String establishmentName,
            @RequestParam int cardNumber, @RequestParam String productDescription,
            @RequestParam double value) throws Exception {
        try {

            service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
