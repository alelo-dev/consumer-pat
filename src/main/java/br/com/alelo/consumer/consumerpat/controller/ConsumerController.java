package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ResultStatus;


@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /**
     * Deve listar todos os clientes (cerca de 500) 
     * @param pageable
     * @return Page<ConsumerDTO>
     */ 
    @GetMapping
    public ResponseEntity<Page<ConsumerDTO>> listAllConsumers(Pageable pageable) {
        Page<ConsumerDTO> allConsumers = consumerService.listAllConsumers(pageable);
        return ResponseEntity.ok(allConsumers);
    }
    /**
     * Cadastrar novos clientes
     * @param consumerDTO
     */
    @PostMapping
    public ResponseEntity<HttpStatus> createConsumer(@RequestBody ConsumerDTO consumerDTO) {
        //TODO validar dados de entrada
        ResultStatus status = consumerService.createConsumer(consumerDTO);
        if (!status.isOk()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Adicionar um cartão a um consumer
     * @param idConsumer
     * @param cardDTO
     * @return
     */
    @PostMapping(value = "/{id}/card/add")
    public ResponseEntity<HttpStatus> addCard(@PathVariable("id") Long idConsumer, @RequestBody CardDTO cardDTO) {
        //TODO validar dados de entrada

        ResultStatus status = consumerService.addCard(idConsumer, cardDTO);
        if (!status.isOk()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Altera as informações de Consumer
     * Não deve ser possível alterar o saldo do cartão
     * @param consumerDTO
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateConsumer(@PathVariable("id") Long id, @RequestBody ConsumerDTO consumerDTO) {
        //TODO validar dados de entrada

        ResultStatus status = consumerService.updateConsumer(consumerDTO);
        if (!status.isOk()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

   

}
