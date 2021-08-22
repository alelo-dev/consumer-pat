package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;
    
    @ResponseBody
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumersService();
    }
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
    	consumerService.createConsumeService(consumer);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
    	consumerService.updateConsumerService(consumerDTO);
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PUT)
    public void setBalance(@RequestBody ConsumerCardDTO consumerCardDTO) {
        consumerService.setBalanceService(consumerCardDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.PUT)
    public void buy(@RequestBody ConsumerCardDTO consumerCardDTO) {
    	consumerService.buyService(consumerCardDTO);
    }

}
