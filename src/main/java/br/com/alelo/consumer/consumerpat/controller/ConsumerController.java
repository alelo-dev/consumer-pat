package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	ConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@RequestMapping("/consumerList")
	public ResponseEntity<Page<Consumer>> listAllConsumers(
			@RequestParam(required = false) @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 500) Pageable paginacao) {
		Page<Consumer> consumer = consumerService.findAllConsumer(paginacao);
		return ResponseEntity.ok(consumer);
	}

	/* Cadastrar novos clientes */
	@PostMapping()
	public ResponseEntity<Boolean> createConsumer(@RequestBody Consumer consumer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.consumerService.saveConsumer(consumer));
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping()
	public void updateConsumer(@RequestBody Consumer consumer) {
		this.consumerService.updateConsumer(consumer);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@GetMapping(value = "/addmoney/{cardNumber}/{value}")
	public void addMoney(@PathVariable int cardNumber, @PathVariable double value) {
		this.consumerService.addMoneyCard(cardNumber, value);
	}

	@GetMapping(value = "/bycard/{establishmentType}/{establishmentName}/{cardNumber}/{productDescription}/{value}")
	public void cardBy(@PathVariable int establishmentType, @PathVariable String establishmentName,
			@PathVariable int cardNumber, @PathVariable String productDescription,@PathVariable double value) {
		//return Integer.toString(establishmentType)+" "+establishmentName+" "+Integer.toString(cardNumber)+
		//		" "+productDescription+" "+Double.toString(value);
		this.consumerService.byCard(establishmentType, establishmentName, cardNumber, productDescription, value);
	}


}
