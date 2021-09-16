package br.com.alelo.consumer.consumerpat.controller;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerBuy;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerSetCardBalance;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerUpdate;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public Page<Consumer> list500Consumers() {
        return consumerService.findConsumers(PageRequest.of(0, 500));
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<?> createConsumer(@Valid @RequestBody Consumer consumer) {
        consumerService.createConsumer(consumer);
        return new ResponseEntity<>(OK);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateConsumer(@PathVariable(required = true, name = "id") Long id, @Valid @RequestBody RequestConsumerUpdate requestConsumerUpdate) {
        consumerService.updateConsumer(id, requestConsumerUpdate);
        return new ResponseEntity<>(OK);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PATCH)
    public ResponseEntity<?> setBalance(@Valid @RequestBody RequestConsumerSetCardBalance requestConsumerSetCardBalance) {
        consumerService.setBalance(requestConsumerSetCardBalance);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<?> buy(@Valid @RequestBody RequestConsumerBuy requestConsumerBuy) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        */
        consumerService.buy(requestConsumerBuy);
        return new ResponseEntity<>(OK);
    }

}
