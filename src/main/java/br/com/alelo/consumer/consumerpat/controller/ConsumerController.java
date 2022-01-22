package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.in.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.in.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.validator.ConsumerValidator;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */

    /**
     * Lista todos os clientes
     *
     * @param pageable
     * @return
     */
    @GetMapping()
    public Page<ResponseConsumerDTO> listAllConsumers(@PageableDefault(size = 100, direction = Sort.Direction.ASC) Pageable pageable) {

        return consumerService.getPageConsumer(pageable);
    }

    /**
     * Cadastrar novos clientes
     *
     * @param createConsumer - Informações do cliente
     */
    @PostMapping
    public ResponseEntity<String> createConsumer(@RequestBody CreateConsumerDTO createConsumer) {
        final String validated = ConsumerValidator.validate(createConsumer);
        if (Objects.isNull(validated)) {
            consumerService.createConsumer(ConsumerConverter.toEntity(createConsumer));
            return ResponseEntity.ok().build();
        } else {
            log.error("Falha validação");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validated);
        }
    }

    /**
     * Atualiza dados do cliente
     * Não deve ser possível alterar o saldo do cartão
     *
     * @param id
     * @param updateConsumerDTO
     */
    @PutMapping(value = "/{id}")
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody UpdateConsumerDTO updateConsumerDTO) {

        consumerService.updateConsumer(id, updateConsumerDTO);
    }
}
