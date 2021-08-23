package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerBuyDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerSetBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	/* Cadastrar novos clientes */
	@PostMapping
	public ResponseEntity<?> createConsumer(@RequestBody ConsumerDto consumerDto) {
		return consumerService.createConsumer(consumerDto);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping
	public ResponseEntity<?> updateConsumer(@RequestBody ConsumerDto consumerDto) {
		return consumerService.updateConsumer(consumerDto);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PostMapping(value = "/balance")
	public void setBalance(@RequestBody ConsumerSetBalanceDto consumerBalanceDto) {

		Integer cardNumber = consumerBalanceDto.getCardNumber();
		BigDecimal value = consumerBalanceDto.getValue();

		Consumer consumer = null;
		consumer = repository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value));
			repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
				repository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = repository.findByFuelCardNumber(cardNumber);
				consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
				repository.save(consumer);
			}
		}
	}

	@PostMapping(value = "/buy")
	public void buy(@RequestBody ConsumerBuyDto consumerBuyDto) {

		Integer establishmentType = consumerBuyDto.getEstablishmentType();
		String establishmentName = consumerBuyDto.getEstablishmentName();
		Integer cardNumber = consumerBuyDto.getCardNumber();
		String productDescription = consumerBuyDto.getProductDescription();
		BigDecimal value = consumerBuyDto.getValue();

		Consumer consumer = null;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		if (establishmentType == 1) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			BigDecimal cashback = value.divide(BigDecimal.valueOf(100l)).multiply(BigDecimal.valueOf(10l));
			value = value.subtract(cashback);

			consumer = repository.findByFoodCardNumber(cardNumber);
			consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(value));
			repository.save(consumer);

		} else if (establishmentType == 2) {
			consumer = repository.findByDrugstoreNumber(cardNumber);
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().subtract(value));
			repository.save(consumer);

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			BigDecimal tax = value.divide(BigDecimal.valueOf(100l)).multiply(BigDecimal.valueOf(35l));
			value = value.add(tax);

			consumer = repository.findByFuelCardNumber(cardNumber);
			consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(value));
			repository.save(consumer);
		}

		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);
	}
}
