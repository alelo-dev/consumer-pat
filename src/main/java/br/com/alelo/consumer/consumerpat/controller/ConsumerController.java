package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping()
    public List<Consumer> listAllConsumers() {
        return service.list();
    }

    /* Cadastrar novos clientes */
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Consumer createConsumer(@RequestBody Consumer consumer) {
        return service.create(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody Consumer consumer) {
        service.update(id, consumer);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Integer id) {
        service.remove(id);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
     * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
     * usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping("/card-balance/{cardNumber}")
    public void setBalance(@PathVariable("cardNumber") int cardNumber, @RequestParam("value") double value) {
        service.setBalance(cardNumber, value);
    }

    @PostMapping("/buy")
    public void buy(@RequestParam("establishmentType") int establishmentType,
            @RequestParam("establishmentName") String establishmentName, @RequestParam("cardNumber") int cardNumber,
            @RequestParam("productDescription") String productDescription, @RequestParam("value") double value) {
        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
