package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ConsumerService consumerService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/consumerlist")
    public List<Consumer> listAllConsumers()  {
        log.info("obtendo todos clientes");
        return consumerService.consumersList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createconsumer")
    public void createConsumer(@RequestBody Consumer consumer) {
        log.info("consumer criado");
        consumerRepository.save(consumer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateConsumer/{id}")
    public void updateConsumer(@PathVariable Integer id ,@RequestBody Consumer updatedConsumer) {
        Consumer consumer = consumerService.updateConsumer(id, updatedConsumer);
        consumerRepository.save(consumer);
    }
}
