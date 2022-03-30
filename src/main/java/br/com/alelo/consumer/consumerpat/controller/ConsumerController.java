package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.interfaces.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<ConsumerDTO> listAllConsumers() {
        return consumerService.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody ConsumerDTO consumer) {
        consumerService.insert(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {
        consumerService.update(consumer);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.PUT)
    public void buy(BuyDTO buy) {
        consumerService.buy(buy);
    }

}
