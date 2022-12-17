package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
        try {
			repository.save(consumer);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
		}
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public void updateConsumer(@RequestBody Consumer consumer) {
    	Consumer consumerToUpdate = validateHasConsumer(consumer.getId());
    	validateCardBalance(consumer, consumerToUpdate);
        repository.save(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);
        
        try {
			if(consumer != null) {
			    // é cartão de farmácia
			    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			    repository.save(consumer);
			} else {
			    consumer = repository.findByFoodCardNumber(cardNumber);
			    if(consumer != null) {
			        // é cartão de refeição
			        consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
			        repository.save(consumer);
			    } else {
			        // É cartão de combustivel
			        consumer = repository.findByFuelCardNumber(cardNumber);
			        if(consumer != null) {
				        consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				        repository.save(consumer);
			        }else {
			        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de cartão inválido");
			        }
			    } 
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
		}
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        try {
			if (establishmentType == 1) {
			    // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			    Double cashback  = (value / 100) * 10;
			    value = value - cashback;

			    consumer = repository.findByFoodCardNumber(cardNumber);
			    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			    repository.save(consumer);

			}else if(establishmentType == 2) {
			    consumer = repository.findByDrugstoreNumber(cardNumber);
			    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			    repository.save(consumer);

			} else if(establishmentType == 3)  {
			    // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			    Double tax  = (value / 100) * 35;
			    value = value + tax;

			    consumer = repository.findByFuelCardNumber(cardNumber);
			    consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			    repository.save(consumer);
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estabecimento inválido");
				}
			} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
		}
        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }
    
    private Consumer validateHasConsumer(Integer id) {
    	Optional<Consumer> consumer = repository.findById(id);
        if(consumer.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return consumer.get();
	}
    
    private void validateCardBalance(Consumer consumer, Consumer consumerToUpdate) {
        if (consumerToUpdate.getFoodCardBalance() != consumer.getFoodCardBalance() ||
        		consumerToUpdate.getDrugstoreCardBalance() != consumer.getDrugstoreCardBalance() ||
        		consumerToUpdate.getFuelCardBalance() != consumer.getFuelCardBalance()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo inválido");
        }
    }  

}
