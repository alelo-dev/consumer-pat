package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerRepository consumerRepository;
    private final CardRepository cardRepository;

    @Autowired
    public ConsumerController(ConsumerRepository consumerRepository, CardRepository cardRepository) {
        this.consumerRepository = consumerRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping()
    public Page<Consumer> listAllConsumers(Pageable page) {
        return consumerRepository.findAll(page);
    }

    @PostMapping()
    @Transactional
    public void createConsumer(@RequestBody ConsumerDTO consumerDTO) {
        consumerRepository.save(consumerDTO.parseConsumer());
    }

    @PutMapping(value = "/{id}")
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody ConsumerDTO consumerDTO) throws Exception {
        Optional<Consumer> consumer = consumerRepository.findById(id);
        if(!consumer.isPresent())
            throw new Exception("O consumer não existe");
        consumerRepository.save(consumer.get().update(consumerDTO));
    }
}
