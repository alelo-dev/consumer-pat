package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @Override
    public void setCardBalance(Integer cardNumber, Double value) throws CardNotFoundException {

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

                if(consumer != null) {
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                    repository.save(consumer);
                }else{
                    throw  new CardNotFoundException("Nenhum cartão encontrado");
                }
            }
        }
    }

    @Override
    public void buy(Integer establishmentType,
                    String establishmentName,
                    Integer cardNumber,
                    String productDescription,
                    Double value) throws CardNotFoundException {

        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        if (EstablishmentType.ALIMENTACAO.getCodigo().equals(establishmentType)) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer == null){
                throw new CardNotFoundException("Cartão não encontrado para o estabelimento => " + establishmentType);
            }
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        }else if(EstablishmentType.FARMACIA.getCodigo().equals(establishmentType)) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            if(consumer == null){
                throw new CardNotFoundException("Cartão não encontrado para o estabelimento => " + establishmentType);
            }
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else if(EstablishmentType.POSTO.getCodigo().equals(establishmentType)){
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            if(consumer == null){
                throw new CardNotFoundException("Cartão não encontrado para o estabelimento => " + establishmentType);
            }

            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        } else {
            throw new RuntimeException("Tipo de estabelecimento não encontrado");
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

    @Override
    public Consumer saveNewConsumer(Consumer consumer) {
        return this.repository.save(consumer);
    }

    @Override
    public Consumer updateConsumer(Consumer consumer) {
        return this.repository.save(consumer);
    }

    @Override
    public List<Consumer> findAll() {
        return this.repository.findAll();
    }

}
