package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.payload.CardRequest;
import br.com.alelo.consumer.consumerpat.service.ConsumerCardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
	
	private final ConsumerCardService consumerCardService;
	
	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão.
	 * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
	 * para isso deve usar o número do cartão(cardNumber) fornecido.
	 */
	@PostMapping(value = "/card-balances/add")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCardBalance(@RequestHeader(name = "cardNumber") String cardNumber, @RequestBody CardRequest cardRequest){
		this.consumerCardService.addBalanceCardNumber(cardNumber, cardRequest);
	}

	@PostMapping(value = "/buys")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void buy(@RequestHeader(name = "cardNumber") String cardNumber, @RequestBody CardRequest cardRequest){
		/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
		 *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
		 *
		 * Tipos de estabelcimentos
		 * 1 - Alimentação (food)
		 * 2 - Farmácia (Drugstore)
		 * 3 - Posto de combustivel (Fuel)
		 */
		this.consumerCardService.removeBalanceCardNumber(cardNumber, cardRequest);
	}
}