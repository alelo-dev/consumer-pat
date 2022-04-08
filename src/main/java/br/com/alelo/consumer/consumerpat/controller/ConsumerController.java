package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    //TODO return DTO
    //TODO fix path
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    @GetMapping("{id}")
    public ResponseEntity<Consumer> getById(@PathVariable int id) {
        var customerFound = repository.findById(id);
        if (customerFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerFound.get());
    }

    /* Cadastrar novos clientes */
    //TODO POST /customers/
    //TODO dto
    //TODO move logict to CustomerService
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public <T> ResponseEntity<T> createConsumer(@RequestBody Consumer consumer) {
        var created = repository.save(consumer);
        var createdCustomerLink = String.format("/customer/%s", created.getId());
        return ResponseEntity.created(URI.create(createdCustomerLink)).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    //TODO PUT /customers/{customerId}
    //TODO dto
    //TODO Move logic to CustomerService
    //TODO Not update the card balance
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public <T> ResponseEntity<T> updateConsumer(@RequestBody Consumer consumer) {
        var found = repository.findById(consumer.getId());
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.save(consumer);
        return ResponseEntity.ok().build();
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    //TODO negative values
    //TODO dto
    //TODO POST /cords/{cardnumber}/recharge
    //TODO extract all logic to CardService
    //TODO refactoring the logic
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public <T> ResponseEntity<T> setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

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
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
        return ResponseEntity.ok().build();
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
            repository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
