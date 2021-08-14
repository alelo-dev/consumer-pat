package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.util.Calculation;
import br.com.alelo.consumer.consumerpat.util.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.util.Validation;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;
    
    @Autowired
    ConsumerService service;
    
    /**
     * @return
     * 
     * Deve listar todos os clientes (cerca de 500) 
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
    	int page = 0; int pageSize = 10;
    	return service.findAll(PageRequest.of(page, pageSize));
    }

    /**
     * @param consumer
     * 
     * Cadastrar novos clientes
     */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    /**
     * @param consumer
     * 
     * Não deve ser possível alterar o saldo do cartão
     */
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.updateConsumer(consumer.getName(), consumer.getDocumentNumber(), consumer.getBirthDate(), 
        							consumer.getMobilePhoneNumber(), consumer.getResidencePhoneNumber(), 
        							consumer.getPhoneNumber(), consumer.getEmail(), consumer.getStreet(), 
        							consumer.getNumber(), consumer.getCity(), consumer.getCountry(), 
        							consumer.getPortalCode(), consumer.getId());
    }

    /**
     * @param cardNumber
     * @param value
     * 
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if(Validation.isNotNull(consumer)) { // é cartão de farmácia
        	addValueDrugstoreCard(consumer, value);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(Validation.isNotNull(consumer)) { // é cartão de refeição
               addValueFoodCard(consumer, value);
            } else { //É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                addValueFuelCard(consumer, value);
            }
        }
    }
    
    /**
     * @param establishmentType
     * @param establishmentName
     * @param cardNumber
     * @param productDescription
     * @param value
     * 
     * Os valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        
        switch (establishmentType) {
	        case TypeEstablishment.FOOD:
	            buyFood(consumer, cardNumber, value);
	        case TypeEstablishment.DRUGSTORE:
	        	buyDrugs(consumer, cardNumber, value);
	        case TypeEstablishment.FUEL:
	        	buyFuel(consumer, cardNumber, value);
        }
        
        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }
    
    /**
     * @param consumer
     * @param cardNumber
     * @param value
     * 
     * Para compras no cartão de alimentação o cliente recebe um desconto de 10%
     */
    private void buyFood(Consumer consumer, int cardNumber, double value) {
        consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - Calculation.cashbackFood(value));
        repository.save(consumer);
    }
    
    /**
     * @param consumer
     * @param cardNumber
     * @param value
     * 
     * Compra em farmacia
     */
    private void buyDrugs(Consumer consumer, int cardNumber, double value) {
    	consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
        repository.save(consumer);
    }
    
    /**
     * @param consumer
     * @param cardNumber
     * @param value
     * 
     * Nas compras com o cartão de combustivel existe um acrescimo de 35%;
     */
    private void buyFuel(Consumer consumer, int cardNumber, double value) {
        consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - Calculation.taxFuel(value));
        repository.save(consumer);
    }
    
    /**
     * @param cardNumber
     * @return
     */
    private void addValueDrugstoreCard(Consumer consumer, double value) {
    	consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
        repository.save(consumer);
    }
    
    /**
     * @param consumer
     * @param value
     */
    private void addValueFoodCard(Consumer consumer, double value) {
    	 consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
         repository.save(consumer);
    }
    
    /**
     * @param consumer
     * @param value
     */
    private void addValueFuelCard(Consumer consumer, double value) {
    	consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
        repository.save(consumer);
    }

}