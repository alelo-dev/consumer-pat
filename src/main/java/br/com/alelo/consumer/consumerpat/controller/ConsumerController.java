package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_BASIC_REQUEST_MAPPING;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_BUY;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_CREATE_CONSUMER;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_LIST_CONSUMERS;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_SET_CARD_BALANCE;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_UPDATE_CONSUMER;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(URI_BASIC_REQUEST_MAPPING)
public class ConsumerController {

    private final ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = URI_LIST_CONSUMERS)
    public List<Consumer> listAllConsumers() {

        log.info("ConsumerController.listAllConsumers - Start");
        log.debug("ConsumerController.listAllConsumers - Start");

        List<Consumer> consumerList = consumerService.listAllConsumers();

        log.debug("ConsumerController.listAllConsumers - End - Output: {}", consumerList);

        return consumerList; //TODO change return type to ResponseEntity maybe?
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = URI_CREATE_CONSUMER)
    public void createConsumer(@RequestBody Consumer consumer) {

        log.info("ConsumerController.createConsumer - Start");
        log.debug("ConsumerController.createConsumer - Start - Input - Consumer: {}", consumer);

        consumerService.createConsumer(consumer); //Change return type to Consumer instead of void
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping(value = URI_UPDATE_CONSUMER)
    public void updateConsumer(@RequestBody Consumer consumer) {

        log.info("ConsumerController.updateConsumer - Start");
        log.debug("ConsumerController.updateConsumer - Start - Input - Consumer: {}", consumer);

        consumerService.updateConsumer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping(value = URI_SET_CARD_BALANCE)
    public void setBalance(int cardNumber, double value) { //TODO verify if this is a query param

        log.info("ConsumerController.setBalance - Start");
        log.debug("ConsumerController.setBalance - Start - Input - Card Number: {}, Value: {}", cardNumber, value);

        consumerService.setBalance(cardNumber, value);
    }

    @ResponseBody
    @GetMapping(value = URI_BUY)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {

        log.info("ConsumerController.buy - Start");
        log.debug("ConsumerController.buy - Start - Input - Establishment Type: {}, Establishment Name: {}, " +
                "Card Number: {}, Product Description: {}, Value: {}", establishmentType, establishmentName, cardNumber,
                productDescription, value);

        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
