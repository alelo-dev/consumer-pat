package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private ConsumerService service;

    @Autowired
    public ConsumerController(ConsumerService service){
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumerList")
    public ResponseEntity<Page<ConsumerDTO>> listAllConsumers(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        log.debug("ConsumerController.listAllConsumers - Begin");
        Pageable pageable = PageRequest.of(page, size);
        log.debug("ConsumerController.listAllConsumers - End");
        return ResponseEntity.ok(service.getAllConsumersList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerDTO> getById(@PathVariable("id") Integer id) {
        log.debug("ConsumerController.getById - Begin");

        log.debug("ConsumerController.getById - End");
        return ResponseEntity.ok(service.getById(id));
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody ConsumerDTO dto) {
        log.debug("ConsumerController.createConsumer - Begin");
        var consumerDTOSave = service.save(dto);
        log.debug("ConsumerController.createConsumer - End");
        return ResponseEntity.ok(consumerDTOSave);
    }

    // Não deve ser possível alterar o saldo do cartão
    // A responsabilidade de atualização de saldo do cartão foi para o EndPoint de cartões.
    @PutMapping("/updateConsumer")
    public ResponseEntity<ConsumerDTO> updateConsumer(@RequestBody ConsumerDTO dto) {
        log.debug("ConsumerController.updateConsumer - Begin");
        var consumerDTOUpdate = service.update(dto);
        log.debug("ConsumerController.updateConsumer - End");
        return ResponseEntity.ok(consumerDTOUpdate);
    }

}
