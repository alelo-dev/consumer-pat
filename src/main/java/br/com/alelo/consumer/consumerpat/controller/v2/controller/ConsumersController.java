package br.com.alelo.consumer.consumerpat.controller.v2.controller;

import br.com.alelo.consumer.consumerpat.controller.v2.resources.ConsumersResource;
import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumersController implements ConsumersResource {

    @Autowired
    private ConsumerService consumerService;

    @Override
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        var consumers = consumerService.getAllConsumers();
        return ResponseEntity.ok(ConsumerDTO.converterListEntityListDto(consumers));
    }

    @Override
    public ResponseEntity<ConsumerDTO> createConsumer(ConsumerDTO consumer) {
        var cons = consumerService.saveConsumer(consumer);
        return ResponseEntity.ok(ConsumerDTO.convertEntityToDto(cons));
    }

    @Override
    public ResponseEntity<ConsumerDTO> updateConsumer(int idConsumer, ConsumerDTO consumer) throws ConsumerNotFoundException {
        var cons = consumerService.updateConsumer(idConsumer, consumer);
        return ResponseEntity.ok(ConsumerDTO.convertEntityToDto(cons));
    }

    @Override
    public ResponseEntity<ConsumerDTO> getConsumerById(int idConsumer) throws ConsumerNotFoundException {
        var cons = consumerService.getConsumerById(idConsumer);
        return ResponseEntity.ok(ConsumerDTO.convertEntityToDto(cons));
    }

    @Override
    public ResponseEntity<ConsumerDTO> getConsumerByCardNumber(int cardNumber) {
        return null;
    }
}
