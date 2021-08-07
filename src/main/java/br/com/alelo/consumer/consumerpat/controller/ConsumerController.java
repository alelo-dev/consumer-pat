package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/consumer")
@Api(value = "Consumidores")
public class ConsumerController {
	
	@Autowired
	IConsumerService service;

	@Operation(summary = "Listagem paginada dos Consumidores", description = "Utilize a listagem paginada para exibição dos consumidores cadastrados no banco de dados.")
	@GetMapping
    public Page<ConsumerDTO> pageConsumers(
    		@Parameter(description = "Número da página", required = false) @RequestParam(value="page", defaultValue="0") Integer page, 
    		@Parameter(description = "Quantidade de itens por página", required = false) @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage) {
        return service.pageConsumers(page, linesPerPage);
    }

	@Operation(summary = "Criar um novo Consumidor", description = "Utilize este endpoint para criação de um novo consumidor.")
    @PostMapping
    public ResponseEntity<Void> createConsumer(@RequestBody ConsumerCreateDTO consumerCreateDTO) {
        Consumer consumer = service.create(consumerCreateDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consumer.getId()).toUri();
		return ResponseEntity.created(uri).build();
    }

    
	@Operation(summary = "Atualizar Consumidor", description = "Utilize este endpoint para atualizar os dados de um consumidor existente.")
    @PutMapping
    public void updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
        service.update(consumerDTO);
    }
    
	@Operation(summary = "Buscar consumidor através do seu ID.", description = "Utilize este endpoint para buscar o consumidor através do seu ID.")
    @GetMapping(value = "/{id}")
    public ConsumerDTO findById(@Parameter(description = "ID do consumidor", example = "1", required = false) @PathVariable Long id) {
        return service.findById(id);
    }
}
