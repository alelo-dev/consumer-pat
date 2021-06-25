package br.com.alelo.consumer.consumerpat.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuy;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerService {


    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    public List<Consumer> listAllConsumers(int initialPage, int limitPage) {
    	 Pageable pageable = PageRequest.of(initialPage, limitPage, Sort.by(Sort.Direction.ASC, "name"));
        Page<Consumer> page =  repository.findAll(pageable);
        
        return page.getContent();
    }
    
    public void consumer(Consumer consumer) {
        repository.save(consumer);
    }
    
    public void updateConsumer(Consumer consumer) {
        repository.save(consumer);
    }
    
    public void setBalance(int cardNumber, double value) {
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
    }
    
    public void buy(int cardNumber,RequestBuy requestBuy) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if (requestBuy.getEstablishmentType() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (requestBuy.getValue() / 100) * 10;
            requestBuy.setValue(requestBuy.getValue() - cashback);

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - requestBuy.getValue());
            repository.save(consumer);

        }else if(requestBuy.getEstablishmentType() == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - requestBuy.getValue());
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (requestBuy.getValue() / 100) * 35;
            requestBuy.setValue(requestBuy.getValue() + tax);

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - requestBuy.getValue());
            repository.save(consumer);
        }

        Extract extract = new Extract(requestBuy.getEstablishmentName(), requestBuy.getProductDescription(), LocalDate.now(), cardNumber, requestBuy.getValue());
        extractRepository.save(extract);
    }
    
    
}
