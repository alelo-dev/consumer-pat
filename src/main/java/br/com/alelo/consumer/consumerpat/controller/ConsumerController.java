package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public PageImpl<ConsumerDTO> listAllConsumers(final Pageable pageable) {
		return this.consumerService.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ConsumerDTO findById(@PathVariable final Long id) {
		return this.consumerService.findConsumerDTOById(id);
	}

	/* Cadastrar novos clientes */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createConsumer(@RequestBody final ConsumerDTO consumeDTO) {
		this.consumerService.save(consumeDTO);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateConsumer(@PathVariable final Long id, @RequestBody final ConsumerDTO consumeDTO) {
		this.consumerService.update(id, consumeDTO);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão.
	 * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
	 * para isso deve usar o número do cartão(cardNumber) fornecido.
	 */
	@PostMapping(value = "/setCardBalance")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void setCardBalance(final String cardNumber, final Double value) throws CardNumberInvalidException, ValueInvalidException {
		this.consumerService.addBalanceCardNumber(cardNumber, value);
	}

	@PostMapping(value = "/buy")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void buy(final Integer establishmentType, final String establishmentName, final String cardNumber, final String productDescription, final Double value) throws CardNumberInvalidEstablishmentTypeException {
		/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
		 *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
		 *
		 * Tipos de estabelcimentos
		 * 1 - Alimentação (food)
		 * 2 - Farmácia (Drugstore)
		 * 3 - Posto de combustivel (Fuel)
		 */
		this.consumerService.removeBalanceCardNumber(establishmentType, establishmentName, cardNumber, productDescription, value);
	}

}