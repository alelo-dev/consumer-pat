package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.vo.BuyVO;
import br.com.alelo.consumer.consumerpat.vo.CardBalanceVO;


@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return service.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        return service.saveConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{idConsumer}", method = RequestMethod.PUT)
    public ResponseEntity<Consumer> updateConsumer(@PathVariable int idConsumer, @RequestBody Consumer consumer) {
    	return service.findById(idConsumer)
    	        .map(record -> {
    	            record.setBirthDate(consumer.getBirthDate());
    	            record.setCity(consumer.getCity());
    	            record.setCountry(consumer.getCountry());
    	            record.setDocumentNumber(consumer.getDocumentNumber());
    	            record.setDrugstoreNumber(consumer.getDrugstoreNumber());
    	            record.setEmail(consumer.getEmail());
    	            record.setFoodCardNumber(consumer.getFoodCardNumber());
    	            record.setFuelCardNumber(consumer.getFuelCardNumber());
    	            record.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
    	            record.setName(consumer.getName());
    	            record.setNumber(consumer.getNumber());
    	            record.setPhoneNumber(consumer.getPhoneNumber());
    	            record.setPortalCode(consumer.getPortalCode());
    	            record.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
    	            record.setStreet(consumer.getStreet());
    	            Consumer updated = service.saveConsumer(record);
    	            return ResponseEntity.ok().body(updated);
    	        }).orElse(ResponseEntity.notFound().build());
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public ResponseEntity<?> setBalance(@RequestBody CardBalanceVO data) {
        try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.setBalance(data.getCardNumber(), data.getValue()));
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<?> buy(@RequestBody BuyVO data) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.buy(data.getEstablishmentType(),
					data.getEstablishmentName(), data.getCardNumber(), data.getProductDescription(), data.getValue()));
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
