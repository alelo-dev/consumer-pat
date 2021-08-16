package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.consumer.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/consumers")
@Api(tags = "Consumers", value = "Consumer", description = "Gerenciamento do cliente")
public class ConsumerController {

   
    @Autowired
    ConsumerService consumerService;	
    
    /**
     * Retorna cliente pelo identificador.
     * 
     * @param id
     * @return ResponseEntity<ConsumerDTO>
     */
    @ApiOperation(value = "Retorna cliente pelo identificador.")  
    @GetMapping("/{id}")
	public  ResponseEntity<ConsumerDTO> getConsumer(@PathVariable("id") Long id) {
    	ConsumerDTO consumer = consumerService.getConsumer(id);    	
		return ResponseEntity.ok(consumer);
	}	
    
    /**
     * Lista todos os clientes.
     * 
     * @return List<Consumer>
     */
    @ApiOperation(value = "Lista todos os clientes.")  
    @GetMapping
    public ResponseEntity<Page<ConsumerDTO>> listAllConsumers(
    		@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="limit",defaultValue="10") int limit,
			@RequestParam(value="direction",defaultValue="asc") String direction) {
    	
    	var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"id"));
    	Page<ConsumerDTO> consumers = consumerService.getConsumers(pageable);
    	    	
    	return new ResponseEntity<>(consumers,HttpStatus.OK);    	
    }
 
    /**
     * Cadastrar novos clientes.
     * 
     * @param consumerDTO
     * @return ResponseEntity<ConsumerDTO>
     */
    @ApiOperation(value = "Cadastro de novos clientes.")     
    @PostMapping
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody ConsumerDTO consumerDTO) {
    	ConsumerDTO consumer = consumerService.saveConsumer(consumerDTO);        	
    	return ResponseEntity.status(HttpStatus.CREATED).body(consumer);
    }   
    
    /**
     * Atualiza dados do cliente, sem atualizar o saldo do cartão.
     * 
     * @param consumerDTO
     * @return ResponseEntity<ConsumerDTO>
     */
    @ApiOperation(value = "Atualizar dados do cliente.")
    @PutMapping()
    public ResponseEntity<ConsumerDTO> updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
    	ConsumerDTO consumer =consumerService.updateConsumer(consumerDTO);     	
    	return ResponseEntity.ok(consumer);		
    }    

}
