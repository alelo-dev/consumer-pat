package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ConsumerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    private ConsumerService service;

    public ConsumerController(ConsumerServiceImpl service) {
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getConsumers());
    }

    /* Cadastrar novos clientes */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody ConsumerDTO consumer) {
        service.saveConsumer(consumer);
    }

    @ResponseBody
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {
        service.updateConsumer(consumer);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public void setBalance(int cardNumber, double value) {
        service.setBalance(cardNumber,value);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        service.buy(establishmentType,establishmentName,cardNumber,productDescription,value);
    }
}
