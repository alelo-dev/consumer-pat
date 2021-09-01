package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.ConsumerEnum;
import br.com.alelo.consumer.consumerpat.util.ConsumerUtil;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {


	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;
	
	/* Deve listar todos os clientes (cerca de 500) */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	/* Cadastrar novos clientes */
	@ResponseBody
	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public Consumer createConsumer(@RequestBody Consumer consumer) {
		return repository.save(consumer);
	}

	// Não deve ser possível alterar o saldo do cartão
	@ResponseBody
	@RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
	public Consumer updateConsumer(@RequestBody Consumer consumer) {
		return repository.save(consumer);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@ResponseBody
	@RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
	public void setBalance(int cardNumber, double value) {
		Consumer consumer = new Consumer();
		consumer = repository.findByDrugstoreNumber(cardNumber);
		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			repository.save(consumer);
		}
		consumer = repository.findByFoodCardNumber(cardNumber);
		if (consumer != null) {
			// é cartão de refeição
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
			repository.save(consumer);
		}
		consumer = repository.findByFuelCardNumber(cardNumber);
		if (consumer != null) {
				// É cartão de combustivel
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
			repository.save(consumer);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {
		Consumer consumer = new Consumer();
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		if (establishmentType == ConsumerEnum.FOOD_CARD) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			value = value - ConsumerUtil.fuelCardFee(value);

			consumer = repository.findByFoodCardNumber(cardNumber);
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			repository.save(consumer);

		} else if (establishmentType == ConsumerEnum.DRUG_STORE_CARD) {
			consumer = repository.findByDrugstoreNumber(cardNumber);
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			repository.save(consumer);

		} else if (establishmentType == ConsumerEnum.FUEL_CARD) {

			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			value = value + ConsumerUtil.fuelCardFee(value);

			consumer = repository.findByFuelCardNumber(cardNumber);
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			repository.save(consumer);
		}

		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);
	}


}
