package br.com.alelo.consumer.consumerpat.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerBusiness implements ConsumerBusinessInt {

	private static final String SALDO_INSUFICIENTE_MSG = "Saldo insuficiente. Saldo atual R$: ";
	private static final String NUMERO_CARTAO_JA_CADASTRADO_MSG = "Número de cartão já cadastrado: ";
	private static final String ALTERAR_SALDO_CARTAO_EXCEPTION_MSG = "Não é possivel alterar o saldo do cartão ";
	private static final Double MAX_VALUE = 999999999D;

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ExtractRepository extractRepository;

	@Override
	public List<Consumer> listAllConsumers() {
		return consumerRepository.getAllConsumersList();
	}

	@Override
	public Consumer createConsumer(Consumer consumer) throws Exception {

		Consumer newConsumer = consumerRepository.getConsumerById(consumer.getId());
		validateNewConsumer(consumer, newConsumer);

		return consumerRepository.save(consumer);
	}

	@Override
	public Consumer updateConsumer(Consumer consumer) throws Exception {

		Consumer editConsumer = consumerRepository.getConsumerById(consumer.getId());
		validateEditConsumer(consumer, editConsumer);

		return consumerRepository.save(consumer);
	}

	private void validateEditConsumer(Consumer consumer, Consumer editConsumer) throws Exception {
		if (null == editConsumer) {
			throw new Exception("Consumidor com ID " + consumer.getId() + " não encontrado !");
		}
		if (changedBalanceForSameDrugNumberCard(consumer, editConsumer)) {
			throw new Exception(ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getDrugstoreCardNumber());
		} else {
			if (changedBalanceForSameFoodNumberCard(consumer, editConsumer)) {
				throw new Exception(ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getFoodCardNumber());
			} else {
				if (changedBalanceForSameFuelNumberCard(consumer, editConsumer)) {
					throw new Exception(ALTERAR_SALDO_CARTAO_EXCEPTION_MSG + editConsumer.getFuelCardNumber());
				}
			}
		}
	}

	@Override
	public void addBalance(long cardNumber, double value) throws Exception {

		validateCardValueTransaction(value);

		Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			consumerRepository.save(consumer);
		} else {
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				consumerRepository.save(consumer);
			} else {
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);

				if (null == consumer) {
					throw new Exception("Número de cartão não encontrado !");
				}
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				consumerRepository.save(consumer);
			}
		}
	}

	@Override
	public Consumer buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value)
			throws Exception {

		Consumer consumer;

		validateEstabType(establishmentType);
		validateCardValueTransaction(value);

		if (establishmentType == 1) {
			final int FOOD_DISCOUNT = 10;
			Double cashback = (value / 100) * FOOD_DISCOUNT;
			value = value - cashback;

			consumer = validateBuyWithFoodCard(cardNumber, value);

			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			consumerRepository.save(consumer);
		} else if (establishmentType == 2) {

			consumer = validateBuyWithDrugCard(cardNumber, value);

			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			consumerRepository.save(consumer);
		} else {
			final int FUEL_ADD_TAX = 35;
			Double tax = (value / 100) * FUEL_ADD_TAX;
			value = value + tax;

			consumer = validateBuyWithFuelCard(cardNumber, value);

			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			consumerRepository.save(consumer);
		}
		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);

		return consumer;
	}

	private Consumer validateBuyWithFuelCard(int cardNumber, double value) throws Exception {
		Consumer consumer;
		consumer = consumerRepository.findByFuelCardNumber(cardNumber);
		if (null == consumer) {
			throw new Exception("O cartão combustivel nº " + cardNumber + " não foi encontrado !");
		}
		if (consumer.getFuelCardBalance() < value) {
			throw new Exception("Saldo insuficiente. Valor total com a taxa: R$ " + value + " .Saldo atual R$: "
					+ consumer.getFuelCardBalance().intValue());
		}
		return consumer;
	}

	private Consumer validateBuyWithDrugCard(int cardNumber, double value) throws Exception {
		Consumer consumer;
		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
		if (null == consumer) {
			throw new Exception("O cartão farmácia nº " + cardNumber + " não foi encontrado !");
		}
		if (consumer.getDrugstoreCardBalance() < value) {
			throw new Exception(SALDO_INSUFICIENTE_MSG + consumer.getDrugstoreCardBalance().intValue());
		}
		return consumer;
	}

	private Consumer validateBuyWithFoodCard(int cardNumber, double value) throws Exception {
		Consumer consumer;
		consumer = consumerRepository.findByFoodCardNumber(cardNumber);
		if (null == consumer) {
			throw new Exception("O cartão refeição nº " + cardNumber + " não foi encontrado !");
		}
		if (consumer.getFoodCardBalance() < value) {
			throw new Exception(SALDO_INSUFICIENTE_MSG + consumer.getFoodCardBalance().intValue());
		}
		return consumer;
	}

	private void validateEstabType(int establishmentType) throws Exception {
		if (establishmentType < 1 || establishmentType > 3) {
			throw new Exception("Número de estabelecimento inválido !");
		}
	}

	private void validateNewConsumer(Consumer consumer, Consumer newConsumer) throws Exception {
		if (null != newConsumer) {
			throw new Exception("Consumidor com ID " + newConsumer.getId() + " já criado, favor usar o serviço de edição !");
		}
		if (null == newConsumer) {
			Consumer foodConsumer = consumerRepository.findCardNumberForAnyType(consumer.getFoodCardNumber());
			if (null != foodConsumer) {
				throw new Exception(NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getFoodCardNumber());
			} else {
				Consumer drugConsumer = consumerRepository.findCardNumberForAnyType(consumer.getDrugstoreCardNumber());
				if (null != drugConsumer) {
					throw new Exception(NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getDrugstoreCardNumber());
				} else {
					Consumer fuelConsumer = consumerRepository.findCardNumberForAnyType(consumer.getFuelCardNumber());
					if (null != fuelConsumer) {
						throw new Exception(NUMERO_CARTAO_JA_CADASTRADO_MSG + consumer.getFuelCardNumber());
					}
				}
			}
		}
	}

	private void validateCardValueTransaction(double value) throws Exception {
		if (value <= 0 || value > MAX_VALUE) {
			throw new Exception("Valor inválido!");
		}
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
