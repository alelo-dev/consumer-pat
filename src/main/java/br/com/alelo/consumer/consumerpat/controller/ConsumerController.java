package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.findAll();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
        this.consumerService.saveNewConsumer(consumer);
        return ResponseEntity.ok("Success");
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> updateConsumer(@RequestBody Consumer consumer) {
        this.consumerService.updateConsumer(consumer);
        return ResponseEntity.ok("Success");
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public ResponseEntity<String> setBalance(int cardNumber, double value) {

        ResponseEntity returnResponseEntity;
        try {
            this.consumerService.setCardBalance(cardNumber, value);
            returnResponseEntity = ResponseEntity.ok("Success");
        } catch (CardNotFoundException e) {
            returnResponseEntity = ResponseEntity.notFound().build();
        }

        return returnResponseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ResponseEntity<String> buy(int establishmentType,
                                      String establishmentName,
                                      int cardNumber,
                                      String productDescription,
                                      double value) {
        ResponseEntity returnResponseEntity;
        try {
            this.consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
            returnResponseEntity = ResponseEntity.ok("Success");
        } catch (CardNotFoundException e) {
            returnResponseEntity = ResponseEntity.notFound().build();
        }
        return returnResponseEntity;
    }

}
