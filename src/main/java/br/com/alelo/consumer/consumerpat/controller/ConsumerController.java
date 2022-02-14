package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.exception.ConsumerException;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService service;

    @Autowired
    public ConsumerController(final ConsumerService service) {
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() throws ConsumerException {
        return service.getAllConsumersList();

    }

    /* Cadastrar novos clientes */
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) throws ConsumerException {
        service.createConsumer(consumer);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public void updateConsumer(@RequestBody Consumer consumer) throws ConsumerException {
        service.updateConsumer(consumer);

    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/add-balance", method = RequestMethod.POST)
    public void addBalance(int cardNumber, double value) throws ConsumerException {
        service.addBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestBody BuyRequest request) throws ConsumerException {
        service.buy(request.getEstablishmentType(), request.getEstablishmentName(), request.getCardNumber(),
                request.getProductDescription(), request.getValue());
    }

    @ExceptionHandler({ ConsumerException.class })
    public ResponseEntity<String> handleException(ConsumerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
