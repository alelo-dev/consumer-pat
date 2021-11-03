package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final String METHOD_INVOKED = "METHOD HAS BEEN INVOKED FROM CONSUMERCONTROLLER: ";

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/consumerList")
    @ApiOperation("Returns a pageable list of all consumers.")
    public Page<Consumer> listAllConsumers(@PageableDefault(size = 10) Pageable pageable) {
        log.info(METHOD_INVOKED + "consumerList");
        return consumerService.findAll(pageable);
    }

    @PostMapping("/createConsumer")
    @ApiOperation("Creates a new customer.")
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        log.info(METHOD_INVOKED + "createConsumer");
        consumerService.create(consumer);
        return ResponseEntity.ok(consumer);
    }

    @ApiOperation("Updates a costumer.")
    @PutMapping("/updateConsumer/{id}")
    public ResponseEntity<Consumer> updateConsumer(@PathVariable Integer id, @RequestBody Consumer consumer) {
        log.info(METHOD_INVOKED + "updateConsumer");
        consumerService.update(id, consumer);
        return ResponseEntity.ok(consumer);
    }

}
