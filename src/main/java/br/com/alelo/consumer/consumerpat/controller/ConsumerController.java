package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
        Boolean isDuplicate = consumerService
                .isDuplicate(consumer.getFoodCardNumber(), consumer.getFuelCardNumber(), consumer.getDrugstoreNumber());
        String cardExist = consumerService
                .findCard(consumer.getFoodCardNumber(), consumer.getFuelCardNumber(), consumer.getDrugstoreNumber());

        if (cardExist.isEmpty() && !isDuplicate) {
            consumerService.saveConsumer(consumer);
            return new ResponseEntity<>("Consumidor registrado com sucesso", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Nº do cartão " + cardExist + " duplicado.", HttpStatus.BAD_REQUEST);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> updateConsumer(@RequestBody Consumer consumer) {
        Consumer consumerStored = consumerService.findById(consumer.getId());
        if (consumerStored == null) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        if (consumer.getFoodCardBalance() != consumerStored.getFoodCardBalance()
                || consumer.getFuelCardBalance() != consumerStored.getFuelCardBalance()
                || consumer.getDrugstoreCardBalance() != consumerStored.getDrugstoreCardBalance()) {
            return new ResponseEntity<>("Não é permitido alterar o saldo do cartão", HttpStatus.BAD_REQUEST);
        }

        consumerService.saveConsumer(consumer);
        return new ResponseEntity<>("Consumidor atualizado com sucesso", HttpStatus.OK);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public ResponseEntity<String> setBalance(int cardNumber, double value) {
        String cardType = consumerService.findCard(cardNumber);
        if (cardType != null ) {
            consumerService.setCardBalance(cardType, cardNumber, value);
            return new ResponseEntity<>("Operação realizada com sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cartão inválido", HttpStatus.BAD_REQUEST);
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

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            consumerService.saveConsumer(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            consumerService.saveConsumer(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            consumerService.saveConsumer(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
