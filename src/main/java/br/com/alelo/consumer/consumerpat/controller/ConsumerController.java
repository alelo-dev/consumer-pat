package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public List<Consumer> findAll() {
        return consumerService.findAll();
    }

    @PostMapping
    public void create(@RequestBody Consumer consumer) {
        consumerService.create(consumer);
    }

    @PutMapping
    public void update(@RequestBody Consumer consumer) {
        consumerService.update(consumer);
    }

}
