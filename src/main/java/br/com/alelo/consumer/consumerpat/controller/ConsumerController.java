package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.dto.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ConsumerResponse;


@RestController
@RequestMapping("/api")
public class ConsumerController {

 
	@Autowired
	private ConsumerService consumerService;

	
    /* Cadastrar novos clientes */
    @PostMapping("/consumer")
    public ResponseEntity<ConsumerResponse> createConsumer(@RequestBody ConsumerRequest request) {
        return ResponseEntity.ok(consumerService.save(request));
    }
    
    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumer")
    public ResponseEntity<List<ConsumerResponse>> listAllConsumers() {
       return ResponseEntity.ok(consumerService.findAll());
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/consumer/{id}")
    public ResponseEntity<ConsumerResponse> updateConsumer(@PathVariable("id") Long id, @RequestBody ConsumerRequest request) {
    	return ResponseEntity.ok(consumerService.update(id, request));
    }
    
}
