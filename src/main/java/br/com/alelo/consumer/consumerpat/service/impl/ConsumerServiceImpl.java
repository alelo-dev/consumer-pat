package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;

    private static final String PERCENTAGE_OF_DISCOUNT_FOOD = "0.10";
    private static final String PERCENTAGE_OF_ADD_FUEL = "0.35";

    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }

    @Override
    public void update(Consumer consumer) {
        repository
                .findById(consumer.getId())
                .map( consumerExists -> {
                    //Here We are copying all fields less Balances fields
                    BeanUtils.copyProperties(consumer, consumerExists, "foodCardBalance", "fuelCardBalance", "drugstoreCardBalance");
                    //Here We will save objeto
                    repository.save(consumerExists);
                    //just return consumer
                    return consumerExists;
                }).orElseThrow(() -> new ConsumerNotFoundException(
                        "Consumer not found") );
    }

    @Override
    public void save(Consumer consumer) {

        //here we are validation the action of save consumer
        validate(consumer);

        repository.save(consumer);
    }

    public void validate(Consumer consumer){

        //We are following that we shouldn't have any duplicate of document number
        if(repository.existsByDocumentNumber(consumer.getDocumentNumber())){
            throw new ConsumerAlreadyExistsException("Consumer couldnt have document number duplicate");
        }

        if(consumer.getFoodCardNumber() != null && repository.existsByFoodCardNumber(consumer.getFoodCardNumber())){
            throw new ConsumerAlreadyExistsException("Consumer couldnt have food card duplicate");
        }

        if(consumer.getFuelCardNumber() != null && repository.existsByFuelCardNumber(consumer.getFuelCardNumber())){
            throw new ConsumerAlreadyExistsException("Consumer couldnt have fuel card duplicate");
        }

        if(consumer.getDrugstoreNumber() != null && repository.existsByDrugstoreNumber(consumer.getDrugstoreNumber())){
            throw new ConsumerAlreadyExistsException("Consumer couldnt have drugstore card duplicate");
        }

    }

    @Override
    @Transactional
    public void setBalance(Long cardNumber, BigDecimal value) {

        repository
                //we are trying to find the consumer
                .findOneByDrugstoreNumberOrFoodCardNumberOrFuelCardNumber(cardNumber)
                .map(
                        //we go to execute action
                        consumer -> {

                            if(cardNumber.equals(consumer.getDrugstoreNumber())){
                                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value).setScale(2, RoundingMode.HALF_DOWN));
                            }else if(cardNumber.equals(consumer.getFoodCardNumber())){
                                consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value).setScale(2, RoundingMode.HALF_DOWN));
                            }else{
                                consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value).setScale(2, RoundingMode.HALF_DOWN));
                            }

                            return repository.save(consumer);

                }).orElseThrow(() -> new ConsumerNotFoundException(
                        "Consumer not found") );;
    }

    @Override
    @Transactional
    public void buy(int establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        repository
                //we are trying to find the consumer
                .findOneByDrugstoreNumberOrFoodCardNumberOrFuelCardNumber(cardNumber)
                .map(
                        //we go to execute action
                        consumer -> {

                            BigDecimal newValue = BigDecimal.ZERO;

                            //We go to treat the rules of debits food card
                            if(EstablishmentTypeEnum.FOOD.value() == establishmentType){

                                //Apply discount on value
                                BigDecimal cashback = new BigDecimal(PERCENTAGE_OF_DISCOUNT_FOOD).multiply(value);
                                newValue = consumer.getFoodCardBalance().subtract(value.subtract(cashback)).setScale(2, RoundingMode.HALF_DOWN);;

                                //if new balance will be negative, we go to send a exception
                                if(newValue.signum() == -1){
                                    throw new InsufficientFundsFoodCardException("Insufficient Funds of Food Card");
                                }

                                consumer.setFoodCardBalance(newValue);

                            }else if(EstablishmentTypeEnum.DRUGSTORE.value() == establishmentType){

                                //just subtract value received to Drugstore
                                newValue = consumer.getDrugstoreCardBalance().subtract(value).setScale(2, RoundingMode.HALF_DOWN);;

                                //if new balance will be negative, we go to send a exception
                                if(newValue.signum() == -1){
                                    throw new InsufficientFundsDrugstoreCardException("Insufficient Funds of Drugstore Card");
                                }

                                consumer.setDrugstoreCardBalance(newValue);

                            }else if(EstablishmentTypeEnum.FUEL.value() == establishmentType){

                                //apply tax over the value of fuel
                                BigDecimal tax = new BigDecimal(PERCENTAGE_OF_ADD_FUEL).multiply(value);
                                newValue = consumer.getFuelCardBalance().subtract(value.add(tax)).setScale(2, RoundingMode.HALF_DOWN);

                                //if new balance will be negative, we go to send a exception
                                if(newValue.signum() == -1){
                                    throw new InsufficientFundsFuelCardException("Insufficient Funds of Fuel Card");
                                }

                                consumer.setFuelCardBalance(newValue);

                            }else{
                                //if operation is different that we are expect, so we will send a exception too
                                throw new InvalidOperationCardException("Invalid Operation of Card");
                            }

                            //Here we are only save the extract
                            Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value, consumer);
                            extractRepository.save(extract);

                            return repository.save(consumer);

                        }).orElseThrow(() -> new ConsumerNotFoundException(
                        "Consumer not found") );
    }
}
