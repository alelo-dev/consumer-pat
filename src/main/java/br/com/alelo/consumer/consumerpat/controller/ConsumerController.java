package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.BuyService;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    CardService cardService;

    @Autowired
    BuyService buyService;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {

        try {
            consumer = consumerService.createConsumer(consumer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<> ( consumer.getName() + " Criado com sucesso", HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public  ResponseEntity <String> updateConsumer(@RequestBody Consumer consumerUpdate) {
        try {
            consumerUpdate = consumerService.updateConsumer(consumerUpdate);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<> ( consumerUpdate.getName() + "Atualizado com Sucesso", HttpStatus.CREATED);
    }
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public ResponseEntity <String> setBalance(int cardNumber, double value) {
        try{
            cardService.setBalance(cardNumber,value);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<> ( "crédito efetuado com sucesso", HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity <String> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
        try {
            buyService.buy(establishmentType,establishmentName,cardNumber,productDescription,value);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<> ( "crédito efetuado com sucesso", HttpStatus.CREATED);
    }

}
