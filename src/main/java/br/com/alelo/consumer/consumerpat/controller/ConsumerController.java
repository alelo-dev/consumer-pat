package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

//	@Autowired
//	ConsumerService consumerService;
//
//	@Autowired
//	ExtractService extractService;
//
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Consumer>> listAllConsumers() {
//		return  ResponseEntity.ok(consumerService.getAllConsumersList());
//	}
//
//	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.createConsumer(consumer));
//	}
//
//	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
//		return ResponseEntity.status(HttpStatus.OK).body(consumerService.updateConsumer(consumer));
//	}
//
//	@PostMapping(path = "/transaction/card/addBalance", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Consumer> setBalance(int cardNumber, double value) {
//		return ResponseEntity.status(HttpStatus.OK).body(consumerService.setBalance(cardNumber, value));
//	}
//
//
//	@PostMapping(path = "/transaction/card/buy", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Extract> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//		return ResponseEntity.status(HttpStatus.OK).body(extractService.buy(establishmentType, establishmentName, cardNumber, productDescription, value));
//	}

}
