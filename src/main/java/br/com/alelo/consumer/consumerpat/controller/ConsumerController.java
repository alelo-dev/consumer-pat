package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.ConsumerBusiness;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerBusiness consumerBusiness;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping()
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        return ResponseEntity.ok(consumerBusiness.listAllConsumers());
    }

    /* Cadastrar novos clientes */
    @PostMapping()
    public void createConsumer(@RequestBody Consumer consumer) throws ProcessException {
        consumerBusiness.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping()
    public void updateConsumer(@RequestBody Consumer consumer) throws ProcessException {
        consumerBusiness.updateConsumer(consumer);
    }

}
