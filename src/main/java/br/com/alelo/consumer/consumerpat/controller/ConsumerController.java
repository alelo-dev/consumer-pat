package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerRepository repository;

	private ConsumerService consumerService;

	@Autowired
	public ConsumerController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	@GetMapping("/consumerList")
	public ResponseEntity<Page<Consumer>> listAllConsumers(
			@RequestParam(value = "page", required = true, defaultValue = "0") int page,
			@RequestParam(value = "size", required = true, defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Consumer> findAllConsumers = consumerService.findAllConsumers(pageable);
		return ResponseEntity.ok(findAllConsumers);
	}

	@PostMapping("/createConsumer")
	public ResponseEntity<Consumer> createConsumer(@RequestBody @Valid ConsumerDTO consumerDTO) {
		Consumer consumer = consumerService.convertAndSaveConsumer(consumerDTO);
		return ResponseEntity.ok(consumer);
	}

	@PutMapping("/updateConsumer/{consumerId}")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody @Valid ConsumerDTO consumerDTO, @PathVariable(name = "consumerId", required = true) Long consumerId) {
		Consumer consumer = consumerService.updateConsumer(consumerDTO, consumerId);
		return ResponseEntity.ok(consumer);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
//    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
//    public void setBalance(int cardNumber, double value) {
//        Consumer consumer = null;
//        consumer = repository.findByDrugstoreNumber(cardNumber);
//
//        if(consumer != null) {
//            // é cartão de farmácia
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
//            repository.save(consumer);
//        } else {
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            if(consumer != null) {
//                // é cartão de refeição
//                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
//                repository.save(consumer);
//            } else {
//                // É cartão de combustivel
//                consumer = repository.findByFuelCardNumber(cardNumber);
//                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
//                repository.save(consumer);
//            }
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Consumer consumer = null;
//        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
//        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
//        *
//        * Tipos de estabelcimentos
//        * 1 - Alimentação (food)
//        * 2 - Farmácia (DrugStore)
//        * 3 - Posto de combustivel (Fuel)
//        */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            repository.save(consumer);
//
//        }else if(establishmentType == 2) {
//            consumer = repository.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            repository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = repository.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            repository.save(consumer);
//        }
//
//        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
//        extractRepository.save(extract);
//    }

}
