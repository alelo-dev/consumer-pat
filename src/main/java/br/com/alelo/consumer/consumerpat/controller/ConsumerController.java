package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;
    
    @Autowired
    CardsRepository cardsRepository;

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
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("{id}") 
    @ResponseStatus(HttpStatus.NO_CONTENT)    
    public void updateConsumer(@PathVariable Integer id,
            					@RequestBody @Valid Consumer newConsumer) {
    	repository
        	.findById(id)
        	.map( consumer -> {
        		consumer.setName(newConsumer.getName());
        		consumer.setDocumentNumber(newConsumer.getDocumentNumber());
        		consumer.setBirthDate(newConsumer.getBirthDate());
        		consumer.setContato(newConsumer.getContato());
        		consumer.setAdress(newConsumer.getAdress());
        		
        		repository.save(consumer);
        	});
        
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        Cards cards = null;
        cards = cardsRepository.findByCardNumber(cardNumber);

        cards.setCardBalance(cards.getCardBalance() + value);
        cardsRepository.save(cards);
        
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    	Cards cards = null;
        cards = cardsRepository.findByCardNumber(cardNumber);
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            If(cards.getCardType() == CardType.FOOD){
	            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
	            cardsRepository.save(cards);
	            
            }

        }else if(establishmentType == 2) {
        	If(cards.getCardType() == CardType.DRUGSTORE){
	            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
	            cardsRepository.save(cards);	            
            }

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            If(cards.getCardType() == CardType.FUEL){
	            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
	            cardsRepository.save(cards);
	            
            }
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract); 
    }

}
