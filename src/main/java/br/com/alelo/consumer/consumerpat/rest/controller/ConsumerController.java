package br.com.alelo.consumer.consumerpat.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public ResponseEntity<List<Consumer>> listAllConsumers() {
    	return ResponseEntity.ok(service.listAllConsumers());
    }


    /* Cadastrar novos clientes */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
    	return ResponseEntity.ok(service.createConsumer(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/updateConsumer")
    public ResponseEntity<Consumer> updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
    	Consumer consumer = toEntity(consumerDTO);
    	return ResponseEntity.ok(service.updateConsumer(consumer));
    }
    
    //Nesse método eu usaria o ModelMapper, mas como está descrito no readme que não posso usar lib, fiz dessa forma!
	private Consumer toEntity(ConsumerDTO consumerDTO) {
		
		Consumer consumer = new Consumer();
		
		consumer.setName(consumerDTO.getName());
		consumer.setDocumentNumber(consumerDTO.getDocumentNumber());
		consumer.setBirthDate(consumerDTO.getBirthDate());
		consumer.setAdress(consumerDTO.getAdress());
		consumer.setContacts(consumerDTO.getContacts());
		
		return consumer;
		
	}

}
