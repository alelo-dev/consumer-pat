package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Api("API Consumers")
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listed all consumers")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "A new consumer saved inside the database of Alelo"),
            @ApiResponse(code = 500, message = "It happens when we find another consumer with the same information"),
    })
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "The consumer's information changed"),
            @ApiResponse(code = 400, message = "It happens when we don't find the consumer"),
    })
    public void updateConsumer(@RequestBody @ApiParam("JSON with consumer's data") Consumer consumer) {
        consumerService.update(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = "/setcardbalance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Defined a new value of foods card, fuel card and drugstore card."),
            @ApiResponse(code = 400, message = "It happens when we don't find the consumer"),
    })
    public void setBalance(@ApiParam("Consumer card number") Long cardNumber, @ApiParam("New value to add in consumer's card")BigDecimal value) {
        consumerService.setBalance(cardNumber, value);
    }

    @PostMapping(value = "/buy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Executed a new purchase with consumer's card."),
            @ApiResponse(code = 400, message = "It happens when we don't find the consumer"),
            @ApiResponse(code = 500, message = "The balance doesn't have limit enough"),
    })
    public void buy(@ApiParam("Establishment type of the purchase") int establishmentType,
                    @ApiParam("Establishment name of the purchase") String establishmentName,
                    @ApiParam("Consumer card number") Long cardNumber,
                    @ApiParam("Product description of the purchase") String productDescription,
                    @ApiParam("Value of the purchase")BigDecimal value) {
        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
