package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired ConsumerService consumerService;

  @Autowired ExtractRepository extractRepository;

  /* Deve listar todos os clientes (cerca de 500) */
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/consumerList")
  public Page<Consumer> listAllConsumers(@PageableDefault(size = 50) Pageable pageable) {
    return consumerService.findAll(pageable);
  }

  @PostMapping("/createConsumer")
  public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
    consumerService.create(consumer);
    return ResponseEntity.ok(consumer);
  }

  @PutMapping("/updateConsumer/{id}")
  public ResponseEntity<Consumer> updateConsumer(
      @PathVariable Integer id, @RequestBody Consumer consumer) {
    consumerService.update(id, consumer);
    return ResponseEntity.ok(consumer);
  }
}
