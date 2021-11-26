package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.ConsumerEnum;
import br.com.alelo.consumer.consumerpat.exception.CardException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerRepository.getAllConsumersList();
    }


    /* Cadastrar novos clientes */

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public @ResponseBody String createConsumer(@RequestBody Consumer consumer) throws Exception{
        if (!consumerRepository.existsById(consumer.getId())){
            consumerRepository.save(consumer);
        }
        return "Cadastrado com Sucesso";
    }

    // Não deve ser possível alterar o saldo do cartão

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public @ResponseBody String updateConsumer(@RequestBody Consumer consumerUpdate) {
        Optional<Consumer> consurmer = consumerRepository.findById(consumerUpdate.getId());
        if (consumerUpdate.getFoodCardBalance() != consumerUpdate.getFuelCardBalance()) {
           return ("Saldo do cartão Alimentação não pode ser alterado");
        }
        if (consumerUpdate.getDrugstoreCardBalance() != consumerUpdate.getDrugstoreCardBalance()) {
            return ("Saldo do cartão Farmácia não pode ser alterado");
        }
        if (consumerUpdate.getFuelCardNumber() != consumerUpdate.getFuelCardNumber()) {
            return ("Saldo do cartão Combustível não pode ser alterado");
        }
        consumerRepository.save(consumerUpdate);
        return "Alterado Com Sucesso";
        }
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public @ResponseBody String setBalance(int cardNumber, double value) throws CardException {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

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
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                if (consumer != null) {
                    // É cartão de combustivel
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                    consumerRepository.save(consumer);
                } else {
                    throw new CardException("Numero do Cartão não encontrado");
                }
            }
        }
        return "Crédito foi efetuado com sucesso";
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public @ResponseBody String buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws Exception {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if ((establishmentType < ConsumerEnum.FOOD_CARD.getValue()) || (establishmentType > ConsumerEnum.FUEL_CARD.getValue())){
            throw new InvalidParameterException("Tipo de estabelecimento invalido");
        }

        if (establishmentType == ConsumerEnum.FOOD_CARD.getValue()) {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if(cardNumber == consumer.getFoodCardNumber()){
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                Double cashback  = (value / 100) * 10;
                value = value - cashback;
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                consumerRepository.save(consumer);
            } else {
                return "Número do cartão Alimentação invalido";
            }

        }else if(establishmentType ==  ConsumerEnum.DRUG_STORE_CARD.getValue()) {
            consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
            if(cardNumber == consumer.getDrugstoreNumber()) {
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                consumerRepository.save(consumer);
            } else {
                return "Número do cartão Farmácia invalido";
            }

        } else if (establishmentType == ConsumerEnum.FUEL_CARD.getValue()){
            consumer = consumerRepository.findByFuelCardNumber(cardNumber);
            if(cardNumber == consumer.getFuelCardNumber()) {
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                Double tax = (value / 100) * 35;
                value = value + tax;
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                consumerRepository.save(consumer);
            } else {
                return "Número do cartão Combustivel invalido";
            }
        }

        Extract extract = new Extract(establishmentType, establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
        return "Compra Efetuada";
    }

}
