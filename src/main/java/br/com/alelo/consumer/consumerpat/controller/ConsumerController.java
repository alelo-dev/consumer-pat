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

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumerator.CardEnum;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.strategy.Context;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	/**
	 * Deve listar todos os clientes (cerca de 500)
	 * 
	 * http://localhost:8585/consumer/consumerList
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	/**
	 * Cadastrar novos clientes
	 * 
	 * http://localhost:8585/consumer/createConsumer
	 * 
	 * @param consumer
	 */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public void createConsumer(@RequestBody Consumer consumer) {
		// Se passarmos o mesmo id, faz update e nao queremos isso, pois ja existe um
		// metodo p/ atualizacao updateConsumer.

		// Se nao existir, entao inseri.
		if (!repository.existsById(consumer.getId())) {
			repository.save(consumer);
		}
	}

	/**
	 * Não deve ser possível alterar o saldo do cartão
	 * 
	 * http://localhost:8585/consumer/updateConsumer
	 * 
	 * @param consumer
	 */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
	public void updateConsumer(@RequestBody Consumer consumer) {
		// Se existir, entao atualiza.
		if (repository.existsById(consumer.getId())) {
			repository.save(consumer);
		}
	}

	/**
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 * 
	 * Ex:
	 * http://localhost:8585/consumer/setcardbalance?cardNumber=111111&value=10000.
	 * 57
	 * 
	 * @param id
	 * @param cardNumber
	 * @param value
	 */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
	public void setBalance(Integer id, int cardNumber, double value) {
		Consumer consumer = null;
		consumer = repository.findByDrugstoreNumber(id, cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(id, cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				repository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = repository.findByFuelCardNumber(id, cardNumber);

				if (consumer != null) {
					consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
					repository.save(consumer);
				}
			}
		}
	}

	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ExtractDTO buy(@RequestBody ExtractDTO param) {
		Consumer consumer = null;

		if (validaCamposObrigatorios(param)) {
			/*
			 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
			 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
			 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
			 * cartão e alimentação
			 *
			 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
			 * Posto de combustivel (Fuel)
			 */
			Context ctx = new Context(CardEnum.getEnum(param.getEstablishmentType()));

			consumer = ctx.getInstance().buy(param, consumer, repository);

			Extract extract = new Extract(param.getId(), param.getEstablishmentNameId(), param.getEstablishmentName(),
					param.getProductDescription(), new Date(), param.getCardNumber(), param.getValue());

			if (consumer != null) {
				if (extract != null) {
					// Inseri uma nova compra p/ um proximo id.
					if (extractRepository.getAllExtractList() != null) {
						Integer lastId = 0;
						List<Extract> allExtractList = extractRepository.getAllExtractList();

						for (Extract extr : allExtractList) {
							if (extr.getId() != null) {
								lastId = extr.getId();
							}
						}

						extract.setId(lastId + 1);
					}

					extractRepository.save(extract);
				}
			}
		}

		return param;
	}

	private Boolean validaCamposObrigatorios(ExtractDTO param) {
		if (param.getId() == null) {
			param.setMensagem("O campo ID é um campo Obrigatório !");

			return false;
		}

		if (param.getCardNumber() == null) {
			param.setMensagem("O campo cardNumber é um campo Obrigatório !");

			return false;
		}

		if (param.getEstablishmentNameId() == null) {
			param.setMensagem("O campo establishmentNameId é um campo Obrigatório !");

			return false;
		}

		if (param.getValue() == null) {
			param.setMensagem("O campo value é um campo Obrigatório !");

			return false;
		}

		return true;
	}
}
