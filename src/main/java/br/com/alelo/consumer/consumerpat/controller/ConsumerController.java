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

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	private static final String SALDO_INSUFICIENTE_MSG = "Saldo insuficiente. Saldo atual R$: ";
	private static final String NUMERO_CARTAO_JA_CADASTRADO_MSG = "Número de cartão já cadastrado: ";
	private static final String ALTERAR_SALDO_CARTAO_EXCEPTION_MSG = "Não é possivel alterar o saldo do cartão ";

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

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public @ResponseBody String createConsumer(@RequestBody Consumer consumer) {

		Consumer newConsumer = repository.getConsumerById(consumer.getId());

		if (null != newConsumer) {
			return "Consumidor já criado, favor usar o serviço de edição !";
		}
		if (null == newConsumer) {
			Consumer foodConsumer = repository.findCardNumberForAnyType(consumer.getFoodCardNumber());
			if (null != foodConsumer) {
				return NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getFoodCardNumber();
			} else {
				Consumer drugConsumer = repository.findCardNumberForAnyType(consumer.getDrugstoreCardNumber());
				if (null != drugConsumer) {
					return NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getDrugstoreCardNumber();
				} else {
					Consumer fuelConsumer = repository.findCardNumberForAnyType(consumer.getFuelCardNumber());
					if (null != fuelConsumer) {
						return NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getFuelCardNumber();
					}
				}
			}
		}
		repository.save(consumer);
		return "Consumidor " + consumer.getName() + " criado com sucesso !";
	}

	// Não deve ser possível alterar o saldo do cartão
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
	public @ResponseBody String updateConsumer(@RequestBody Consumer consumer) {

		Consumer editConsumer = repository.getConsumerById(consumer.getId());

		if (null == editConsumer) {
			return "Consumidor com ID " + consumer.getId() + " não encontrado !";
		}
		if (changedBalanceForSameDrugNumberCard(consumer, editConsumer)) {
			return ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getDrugstoreCardNumber();
		} else {
			if (changedBalanceForSameFoodNumberCard(consumer, editConsumer)) {
				return ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getFoodCardNumber();
			} else {
				if (changedBalanceForSameFuelNumberCard(consumer, editConsumer)) {
					return ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getFuelCardNumber();
				}
			}
		}
		repository.save(consumer);
		return "Consumidor " + consumer.getName() + " alterado com sucesso !";
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
	public @ResponseBody String setBalance(long cardNumber, double value) {

		Consumer consumer = repository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				repository.save(consumer);
			} else {
				consumer = repository.findByFuelCardNumber(cardNumber);

				if (null == consumer) {
					return "Número de cartão não encontrado !";
				}
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				repository.save(consumer);
			}
		}
		return "Crédito efetuado com sucesso !";
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public @ResponseBody String buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {

		Consumer consumer;

		if (establishmentType < 1 || establishmentType > 3) {
			return "Número de estabelecimento inválido !";
		}
		if (establishmentType == 1) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			final int FOOD_DISCOUNT = 10;
			Double cashback = (value / 100) * FOOD_DISCOUNT;
			value = value - cashback;

			consumer = repository.findByFoodCardNumber(cardNumber);
			if (null == consumer) {
				return "O cartão refeição nº " + cardNumber + " não foi encontrado !";
			}
			if (consumer.getFoodCardBalance() < value) {
				return SALDO_INSUFICIENTE_MSG + consumer.getFoodCardBalance();
			}
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			repository.save(consumer);
		} else if (establishmentType == 2) {

			consumer = repository.findByDrugstoreNumber(cardNumber);
			if (null == consumer) {
				return "O cartão farmácia nº " + cardNumber + " não foi encontrado !";
			}
			if (consumer.getDrugstoreCardBalance() < value) {
				return SALDO_INSUFICIENTE_MSG + consumer.getDrugstoreCardBalance();
			}
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			repository.save(consumer);
		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			final int FUEL_ADD_TAX = 35;
			Double tax = (value / 100) * FUEL_ADD_TAX;
			value = value + tax;

			consumer = repository.findByFuelCardNumber(cardNumber);
			if (null == consumer) {
				return "O cartão combustivel nº " + cardNumber + " não foi encontrado !";
			}
			if (consumer.getFuelCardBalance() < value) {
				return "Saldo insuficiente. Valor total com a taxa: R$ " + value + " .Saldo atual R$: " + consumer.getFuelCardBalance();
			}
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			repository.save(consumer);
		}
		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);

		return "Compra com cartão " + cardNumber + " do produto " + productDescription + " efetuada com sucesso !";
	}

	private boolean changedBalanceForSameFuelNumberCard(Consumer consumer, Consumer editConsumer) {
		return !(editConsumer.getFuelCardBalance().equals(consumer.getFuelCardBalance()))
				&& (editConsumer.getFuelCardNumber() == consumer.getFuelCardNumber());
	}

	private boolean changedBalanceForSameFoodNumberCard(Consumer consumer, Consumer editConsumer) {
		return !(editConsumer.getFoodCardBalance().equals(consumer.getFoodCardBalance()))
				&& (editConsumer.getFoodCardNumber() == consumer.getFoodCardNumber());
	}

	private boolean changedBalanceForSameDrugNumberCard(Consumer consumer, Consumer editConsumer) {
		return !(editConsumer.getDrugstoreCardBalance().equals(consumer.getDrugstoreCardBalance()))
				&& (editConsumer.getDrugstoreCardNumber() == consumer.getDrugstoreCardNumber());
	}

}
