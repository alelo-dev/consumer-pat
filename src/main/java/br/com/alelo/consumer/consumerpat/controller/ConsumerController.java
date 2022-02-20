package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @GetMapping
    public Page<ConsumerVo> listAllConsumers(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable page) {
        return service.listAllConsumers(page);
    }

    @PostMapping
    public ConsumerVo createConsumer(@RequestBody ConsumerVo consumer) {
        return service.createConsumer(consumer);
    }

    @PutMapping("/{id}")
    public ConsumerVo updateConsumer(@RequestBody ConsumerVo consumer, @PathVariable Integer id) {
        return service.updateConsumer(consumer, id);
    }

}
