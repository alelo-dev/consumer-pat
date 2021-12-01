package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping(value = URI_LIST_CONSUMERS)
    public ResponseEntity<List<Consumer>> listAllConsumers() {

        log.info("ConsumerController.listAllConsumers - Start");
        log.debug("ConsumerController.listAllConsumers - Start");

        List<Consumer> consumerList = consumerService.listAllConsumers();
        log.debug("ConsumerController.listAllConsumers - End - Output: {}", consumerList);

        return ResponseEntity.ok(consumerList);
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = URI_CREATE_CONSUMER)
    public ResponseEntity<Consumer> createConsumer(@RequestBody final Consumer consumer) {

        log.info("ConsumerController.createConsumer - Start");
        log.debug("ConsumerController.createConsumer - Start - Input: {}", consumer);

        Consumer created = consumerService.createConsumer(consumer);
        ResponseEntity<Consumer> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(created);

        log.debug("ConsumerController.createConsumer - End - Input: {}, Output {}", consumer, created);

        return response;
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = URI_UPDATE_CONSUMER)
    public ResponseEntity<Consumer> updateConsumer(@RequestBody final Consumer consumer) {

        log.info("ConsumerController.updateConsumer - Start");
        log.debug("ConsumerController.updateConsumer - Start - Input: {}", consumer);

        Consumer updated = consumerService.updateConsumer(consumer);
        ResponseEntity<Consumer> response = ResponseEntity.ok(updated);

        log.debug("ConsumerController.updateConsumer - End - Input: {}, Output {}", consumer, updated);

        return response;
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = URI_SET_CARD_BALANCE)
    public void setCardBalance(@Param("cardNumber") final Integer cardNumber,
                               @Param("value") final double value) {

        log.info("ConsumerController.setBalance - Start");
        log.debug("ConsumerController.setBalance - Start - Input - Card Number: {}, Value: {}", cardNumber, value);

        consumerService.setCardBalance(cardNumber, value); //TODO should it return the new balance?
    }

    @ResponseBody
    @PostMapping(value = URI_BUY)
    public void buy(@Param("establishmentType") final int establishmentType,
                    @Param("establishmentName") final String establishmentName,
                    @Param("cardNumber") final int cardNumber,
                    @Param("productDescription") final String productDescription,
                    @Param("value") final double value) {

        log.info("ConsumerController.buy - Start");
        log.debug("ConsumerController.buy - Start - Input - Establishment Type: {}, Establishment Name: {}, " +
                "Card Number: {}, Product Description: {}, Value: {}", establishmentType, establishmentName, cardNumber,
                productDescription, value);

        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
