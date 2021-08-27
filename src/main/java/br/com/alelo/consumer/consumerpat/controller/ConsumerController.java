package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PUT)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        try{
            if(consumer != null) {
                // é cartão de farmácia
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
                consumerRepository.save(consumer);
            } else {
                consumer = consumerRepository.findByFoodCardNumber(cardNumber);
                if(consumer != null) {
                    // é cartão de refeição
                    consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                    consumerRepository.save(consumer);
                } else {
                    // É cartão de combustivel
                    consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                    consumerRepository.save(consumer);
                }
            }
        }catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found!");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
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
        try{
            switch (establishmentType){
                case 1:
                    Double cashback  = (value / 100) * 10;
                    value = value - cashback;

                    consumer = consumerRepository.findByFoodCardNumber(cardNumber);
                    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                    consumerRepository.save(consumer);
                    break;

                case 2:
                    consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
                    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                    consumerRepository.save(consumer);
                    break;

                default:
                    // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                    Double tax  = (value / 100) * 35;
                    value = value + tax;

                    consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                    consumerRepository.save(consumer);
                    break;
            }
        } catch(NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card invalid");
        }

        Extract extract = new Extract(consumer.getId(), establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}

