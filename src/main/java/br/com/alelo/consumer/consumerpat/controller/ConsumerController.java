package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import br.com.alelo.consumer.consumerpat.data.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidIdException;
import br.com.alelo.consumer.consumerpat.domain.exception.UnknownConsumerException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    CardService cardService;

    @Value("${pagination.page.size.consumer}")
    private int defaultConsumerPageSize;

    @ResponseBody
    @GetMapping
    public Page<Consumer> listAllConsumers(Integer size, Integer page) {
        return consumerService.listAllConsumers(
                size == null ? defaultConsumerPageSize : size,
                page == null ? 0 : page
        );
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        return consumerService.createConsumer(consumer.setId(null));
    }

    @PutMapping
    public Consumer updateConsumer(@RequestBody Consumer consumer) throws UnknownConsumerException, InvalidIdException {
        return consumerService.updateConsumer(consumer);
    }

    @GetMapping("/{consumerId}/card")
    public List<Card> findConsumerCards(@PathVariable int consumerId) {
        return cardService.findByConsumerId(consumerId);
    }

    @GetMapping("/{consumerId}")
    public Consumer findConsumer(@PathVariable int consumerId) throws UnknownConsumerException {
        return consumerService.findById(consumerId);
    }
}
