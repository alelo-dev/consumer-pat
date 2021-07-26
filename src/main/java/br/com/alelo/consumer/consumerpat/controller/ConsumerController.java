package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumersDto;
import br.com.alelo.consumer.consumerpat.facade.ConsumerFacade;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consumers")
@AllArgsConstructor
public class ConsumerController {
    
    private final ConsumerFacade facade;
    
    /**
     * Cadastrar novos clientes
     *
     * @param consumer
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerDto createConsumer(@RequestBody final ConsumerSaveDto consumer) {
        return facade.save(consumer);
    }
    
    /**
     * Deve listar todos os clientes (cerca de 500)
     *
     * @param pageable
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ConsumersDto listAll(final Pageable pageable) {
        return facade.findAll(pageable);
    }
    
    /**
     * Não deve ser possível alterar o saldo do cartão.
     *
     * @param consumer update dto
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsumer(@RequestBody final ConsumerUpdateDto consumer) {
        facade.update(consumer);
    }
    
}
