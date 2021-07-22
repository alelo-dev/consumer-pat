package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ConsumerRepository repository;

    @Autowired
    AddressRepository addressRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    @Transactional
    public String createConsumer(@RequestBody ConsumerDTO consumerDTO) {

        Consumer newConsumer = consumerDTO.toModel(manager, addressRepository);
        manager.persist(newConsumer);

        return newConsumer.toString();
    }

    @GetMapping("/{id}")
    public Consumer getById(@PathVariable Integer id) {
        return repository.getOne(id);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/{id}")
    public Consumer updateConsumer(@RequestBody Consumer consumer) {

        return repository.save(consumer);
    }

}
