package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/consumer")
@ApiOperation(tags = "Consumer", value = "Consumer", notes = "Consumer Operations")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;
	
    @ApiOperation(value = "Return consumer by IdConsumer.")  
    @GetMapping("/{id}")
	public  ResponseEntity<Consumer> getConsumer(@PathVariable("id") String id) {
		return ResponseEntity.ok(consumerService.getConsumer(id));
	}	

	@ApiOperation(value = "Return list of all consumers.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Consumer>> getAllConsumers(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "500") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "creationTime"));
		Page<Consumer> consumers = consumerService.getAllConsumers(pageable);
		return ResponseEntity.ok(consumers);
	}
    
	@ApiOperation(value = "Create a new customer.")
	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Consumer> createConsumer(@RequestBody ConsumerDto consumerDto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.createConsumer(consumerDto));
	}

	@ApiOperation(value = "Update a exists customer.")
	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Consumer> updateConsumer(@RequestBody ConsumerDto consumerDto) {
		return ResponseEntity.status(HttpStatus.OK).body(consumerService.updateConsumer(consumerDto));
	}
	
	@ApiOperation(value = "Delete a exists customer.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		return consumerService.deleteConsumer(id);
	}
	
	//TODO add create new address endpoint by user
	
	//TODO add update address endpoint by user
	
	//TODO add delete address endpoint by user
	
	//TODO add create new contact endpoint by user
	
	//TODO add update contact endpoint by user
	
	//TODO add delete contact endpoint user
	
}
