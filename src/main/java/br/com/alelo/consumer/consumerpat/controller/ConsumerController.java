package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.dto.CardsBalanceDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ExtractService extractService;

	/* Deve listar todos os clientes (cerca de 500) */

	@GetMapping(value = "/consumerList")
	public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
		List<Consumer> consumer = consumerService.listAllConsumers();
		List<ConsumerDTO> consumerDTO = consumer.stream().map(obj -> new ConsumerDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(consumerDTO);
	}

	/* Cadastrar novos clientes */
	@PostMapping(value = "/createConsumer")
	public ResponseEntity<Void> createConsumer(@RequestBody Consumer consumer) {
		consumer = consumerService.createConsumer(consumer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consumer.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping(value = "/updateConsumer")
	public ResponseEntity<Void> updateConsumer(@RequestBody ConsumerUpdateDTO consumer) {
		consumerService.updateConsumer(consumer);
		return ResponseEntity.noContent().build();

	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PatchMapping(value = "/setcardbalance")
	public ResponseEntity<Void> setBalance(@RequestBody CardsBalanceDTO cardsDTO) {
		consumerService.setBalance(cardsDTO.getCardNumber(), cardsDTO.getCardBalance());
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/buy")
	public ResponseEntity<Void> buy(@RequestBody BuyDTO dto) {
		
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */
		consumerService.buy(dto);
		extractService.saveExtract(dto);

		return ResponseEntity.noContent().build();
	}

}
