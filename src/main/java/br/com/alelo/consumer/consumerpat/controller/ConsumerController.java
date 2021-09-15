package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.json.BuyJSON;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService service ;

    @Autowired
    ExtractService extractService ;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return service.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED )
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public String createConsumer(@RequestBody Consumer consumer) {
    	service.save(consumer);
    	return "{ \"id\" : \"" + consumer.getId().toString() + "\"}" ;
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseStatus(code = HttpStatus.ACCEPTED )
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody Consumer consumer) {
    	service.save(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    // Alexandre ( 15/09/2021 ) : Set data com GET ???
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
    	
        Consumer consumer = null;
        consumer = service.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            service.save(consumer);
        } else {
            consumer = service.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                service.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = service.findByFuelCardNumber(cardNumber);
                if ( consumer != null ) {
                	// Condição criada por Alexandre em 15/09/2021
                	consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                	service.save(consumer);
                }
            }
        }
    }

    // Alexandre ( 15/09/2021 ) : Troca de parâmetros do método por objeto JSON
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST )
    public ResponseEntity<?> buy( @RequestBody BuyJSON buy ) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if ( buy.getEstablishmentType() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            consumer = service.findByFoodCardNumber(buy.getCardNumber());
            if ( consumer != null ) {
                Double cashback  = ( buy.getValue() / 100) * 10;
                buy.setValue( buy.getValue() - cashback ) ;
            	consumer.setFoodCardBalance( consumer.getFoodCardBalance() - buy.getValue() ) ;
            	service.save(consumer);
            }

        }else if(buy.getEstablishmentType() == 2) {
            consumer = service.findByDrugstoreNumber(buy.getCardNumber());
            if ( consumer != null ) {
            	consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buy.getValue() ) ;
            	service.save(consumer);
            }
        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            consumer = service.findByFuelCardNumber(buy.getCardNumber());
            if ( consumer != null ) {
                Double tax  = ( buy.getValue() / 100) * 35;
                buy.setValue( buy.getValue() + tax ) ;
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - buy.getValue() );
                service.save(consumer);
            }
        }
        
        if ( consumer != null ) {
        	Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(), buy.getCardNumber(), buy.getValue() ) ;
        	extractService.save(extract);
        }
        
        return consumer != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build() ;
    }

}
