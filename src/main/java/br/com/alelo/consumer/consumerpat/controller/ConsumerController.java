package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	IConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConsumerResponseDTO> listAllConsumers(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy) {
		return consumerService.getAllConsumersList(pageNumber, pageSize, sortBy);
	}

	/* Cadastrar novos clientes */
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.CREATED )
	public ResponseEntity<ConsumerResponseDTO> createConsumer(final @Validated @RequestBody ConsumerRequestDTO consumer) throws Exception {
		
		return ResponseEntity.ok(consumerService.createNewConsumer(consumer));
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDTO>  updateConsumer(final @PathVariable Long id, final @RequestBody ConsumerRequestDTO consumer) throws Exception {
		return ResponseEntity.ok(consumerService.updateConsumer(id, consumer));
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão.
	 * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
	 * para isso deve usar o número do cartão(cardNumber) fornecido.
	 */
	@PutMapping(value = "/setcardbalance", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.OK )
	public void setBalance(@RequestBody BalanceDTO balanceDTO) {
		//consumerService.createNewConsumer(balanceDTO)
	}

	@ResponseStatus( HttpStatus.CREATED )
	@PostMapping(value = "/buy")
	public void buy(Integer establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {
		//consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
	}
}
