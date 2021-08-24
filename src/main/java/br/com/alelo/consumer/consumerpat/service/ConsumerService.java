package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerBuyDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerSetBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.ResponseUtil;

@Service
public class ConsumerService {
	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	public ResponseEntity<?> createConsumer(ConsumerDto consumerDto) {
		ResponseEntity<?> output = null;
		try {
			HashMap<String, Object> body = new HashMap<>();

			if (isNull(consumerDto.getFoodCardNumber(), consumerDto.getFuelCardNumber(),
					consumerDto.getDrugstoreNumber())) {
				throw new BusinessException("All card numbers are needed!");
			}

			if (repository.existsCardNumbers(consumerDto.getFoodCardNumber(), consumerDto.getFuelCardNumber(),
					consumerDto.getDrugstoreNumber())) {
				throw new BusinessException("One of the cards has already been registered!");
			}

			Consumer consumer = new Consumer();
			BeanUtils.copyProperties(consumerDto, consumer, "id");
			repository.save(consumer);

			body.put("data", consumer);

			output = ResponseEntity.status(HttpStatus.CREATED).body(body);

		} catch (BusinessException e) {
			output = ResponseUtil.getBadRequestResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			output = ResponseUtil.getInternalErrorResponse();
		}
		return output;
	}

	public ResponseEntity<?> updateConsumer(ConsumerDto consumerDto) {
		ResponseEntity<?> output = null;
		try {
			if (isNull(consumerDto.getFoodCardNumber(), consumerDto.getFuelCardNumber(),
					consumerDto.getDrugstoreNumber())) {
				throw new BusinessException("All card numbers are needed!");
			}

			if (repository.existsCardNumbers(consumerDto.getId(), consumerDto.getFoodCardNumber(),
					consumerDto.getFuelCardNumber(), consumerDto.getDrugstoreNumber())) {
				throw new BusinessException("One of the cards has already been registered!");
			}

			Consumer consumer = repository.findById(consumerDto.getId())
					.orElseThrow(() -> new BusinessException("Consumer not found!"));

			String notUpdateableProps[] = { "id", "foodCardBalance", "fuelCardBalance", "drugstoreCardBalance" };

			BeanUtils.copyProperties(consumerDto, consumer, notUpdateableProps);

			repository.save(consumer);

		} catch (BusinessException e) {
			output = ResponseUtil.getBadRequestResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			output = ResponseUtil.getInternalErrorResponse();
		}

		return output;
	}

	public ResponseEntity<?> setBalance(ConsumerSetBalanceDto consumerBalanceDto) {
		ResponseEntity<?> output = null;

		try {

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

		} catch (BusinessException e) {
			output = ResponseUtil.getBadRequestResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			output = ResponseUtil.getInternalErrorResponse();
		}

		return output;
	}

	public ResponseEntity<?> buy(ConsumerBuyDto consumerBuyDto) {
		ResponseEntity<?> output = null;
		try {
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
		} catch (BusinessException e) {
			output = ResponseUtil.getBadRequestResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			output = ResponseUtil.getInternalErrorResponse();
		}
		return output;
	}

	private boolean isNull(Object... objects) {
		return Arrays.stream(objects).anyMatch(obj -> obj == null);
	}

}
