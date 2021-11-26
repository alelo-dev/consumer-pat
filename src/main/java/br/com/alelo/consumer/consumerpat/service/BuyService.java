package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.ConsumerEnum;
import br.com.alelo.consumer.consumerpat.exception.ConsumerOrCardException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;

@Service
@AllArgsConstructor
public class BuyService {
    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;

    public Extract buy (int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws ConsumerOrCardException {
        Consumer consumer;

        if ((establishmentType < ConsumerEnum.FOOD_CARD.getValue()) || (establishmentType > ConsumerEnum.FUEL_CARD.getValue())){
            throw new InvalidParameterException("Tipo de estabelecimento invalido");
        }

        if (establishmentType == ConsumerEnum.FOOD_CARD.getValue()) {

            consumer = consumerRepository.findByFoodCardNumber(cardNumber);

            if (null == consumer){
                throw new ConsumerOrCardException ("Cartão Alimentação não encontrado." + " Cartão numero: " + cardNumber);
            }
            if (consumer.getFoodCardBalance() < value) {
                throw new ConsumerOrCardException ("Saldo no cartão é insuficiente." + " Saldo: " + consumer.getFoodCardBalance());
            }
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            consumerRepository.save(consumer);
        } else if(establishmentType ==  ConsumerEnum.DRUG_STORE_CARD.getValue()) {
            consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
            if (null == consumer){
                throw new ConsumerOrCardException ("Cartão Farmacia não encontrado." + " Cartão numero: " + cardNumber);
            }
            if (consumer.getDrugstoreCardBalance() < value) {
                throw new ConsumerOrCardException ("Saldo no cartão é insuficiente." + " Saldo: " + consumer.getDrugstoreCardBalance());
            }
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            consumerRepository.save(consumer);
        } else if (establishmentType == ConsumerEnum.FUEL_CARD.getValue()){
            consumer = consumerRepository.findByFuelCardNumber(cardNumber);

            if (null == consumer){
                throw new ConsumerOrCardException ("Cartão Combustivel não encontrado." + " Cartão numero: " + cardNumber);
            }
            if (consumer.getFuelCardBalance() < value) {
                throw new ConsumerOrCardException ("Saldo no cartão é insuficiente." + " Saldo: " + consumer.getFuelCardBalance());
            }
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (value / 100) * 35;
            value = value + tax;
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            consumerRepository.save(consumer);
        }

        Extract extract = new Extract(establishmentType, establishmentName, productDescription, new Date(), cardNumber, value);

        return extractRepository.save(extract);
    }

}
