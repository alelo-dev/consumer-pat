package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/card")
@ApiOperation(tags = "Card", value = "Card", notes = "Card Operations")
public class CardController {

	//TODO Create Card for user
//	@ApiOperation(value = "Create a new customer.")
//	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Consumer> createConsumer(@RequestBody ConsumerDto consumerDto) throws Exception {
//		return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.createConsumer(consumerDto));
//	}
	
	//TODO Delete Card user
//	@ApiOperation(value = "Create a new customer.")
//	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Consumer> createConsumer(@RequestBody ConsumerDto consumerDto) throws Exception {
//		return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.createConsumer(consumerDto));
//	}
	
	//TODO Consult especific card balance
//    @ApiOperation(value = "Return consumer by IdConsumer.")  
//    @GetMapping("/{id}")
//	public  ResponseEntity<Consumer> getConsumer(@PathVariable("id") String id) {
//		return ResponseEntity.ok(consumerService.getConsumer(id));
//	}	
	

}
