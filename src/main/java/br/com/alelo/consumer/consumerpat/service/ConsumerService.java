package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractService extractService;

    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    public String saveConsumer(Consumer consumer) {
        String response;
        if(repository.existsById(consumer.getId())){
            if(consumer.getFoodCardBalance() > 0 || consumer.getDrugstoreCardBalance() > 0 || consumer.getFuelCardBalance() > 0){
                response = "Nao eh permitido alterar o valor disponivel do saldo do cartao";
            }else{
                repository.save(consumer);
                response = "Dados do consumidor atualizados com sucesso";
            }
        }else{
            repository.save(consumer);
            response = "Consumidor criado com sucesso";
        }
        return response;
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    public String setBalance(int cardNumber, double value) {
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
        return "Saldo atualizado com sucesso";
    }

    public String buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        boolean successfulBuy = false;
        Date date = new Date();
        Extract extract = new Extract(establishmentName, productDescription, date, cardNumber, value);
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
            extract.setValue(value);
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer.getFoodCardBalance() - value >= 0){
                successfulBuy = true;
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            }

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            if(consumer.getDrugstoreCardBalance() - value >= 0){
                successfulBuy = true;
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            }            

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;
            extract.setValue(value);
            consumer = repository.findByFuelCardNumber(cardNumber);
            if(consumer.getFuelCardBalance() - value >= 0){
                successfulBuy = true;
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            }
        }
        if(successfulBuy){
            repository.save(consumer);
            extractService.addRegisterToExtract(extract);
            return "Compra realizada com sucesso!";
        }else{
            return "Saldo insuficiente";
        }
    }
    
}
