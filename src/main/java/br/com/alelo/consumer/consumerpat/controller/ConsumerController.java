package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.request.AddBalanceRequest;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    CardService cardService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/listAll")
    public List<Consumer> listAllConsumers() {
        return consumerService.findAll();
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/create")
    public void createConsumer(@RequestBody Consumer consumer) {

        consumerService.createConsumer(consumer);
    }

    @ResponseBody
    @GetMapping(value = "/findById/{id}")
    public Consumer findConsumer(@PathVariable(value = "id") Long id) {

        return consumerService.findById(id);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/update")
    public void updateConsumer(@RequestBody Consumer consumer) {

        consumerService.updateConsumer(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "/addCardBalance")
    public void addCardBalance(@RequestBody AddBalanceRequest addBalanceRequest) {

        cardService.addBalance(addBalanceRequest);
    }

    @PostMapping(value = "/buy")
    public void buy(@RequestBody BuyRequest buyRequest) {

        cardService.buySomething(buyRequest);
    }

}
