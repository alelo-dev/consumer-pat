package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
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
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerService.update(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = "/setcardbalance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setBalance(Long cardNumber, BigDecimal value) {
        consumerService.setBalance(cardNumber, value);
    }

    @PostMapping(value = "/buy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void buy(int establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {
        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
