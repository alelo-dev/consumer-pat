package br.com.alelo.consumer.consumerpat.Service;

import br.com.alelo.consumer.consumerpat.entity.BenefitCard;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.BenefitCardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    BenefitCardRepository benefitCardRepository;

    @Autowired
    ExtractRepository extractRepository;

    Consumer consumer;
    BenefitCard benefitCard;

    /* Listagem dos Cliente */
    public List<Consumer> listConsumerService(){
        return consumerRepository.findAll();
    }

    /* Cadastra novo Cliente */
    public Consumer createConsumerService(Consumer consumer){
        try {
            return consumerRepository.save(consumer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void recordBuy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value){
        //Consumer consumer = null; OK
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        BenefitCard benefitCard = benefitCardRepository.findByCardNumber(cardNumber).get();
        int id = benefitCard.getBenefitType_id().getId();
        //todo: Mudar para join em uma nova table
        switch(id) {
            case 1:
                //
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                Double cashback = (value / 100) * 10;
                value = value - cashback;
                break;
            case 2:
                // code block
//                consumer = repository.findByDrugstoreNumber(cardNumber);
                value = value;
                break;
            case 3:
                // code block
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                Double tax  = (value / 100) * 35;
                value = value + tax;
                break;
            default:
                // code block
                value = value;
            }

        benefitCard.setCardBalance(benefitCard.getCardBalance() - value);
        benefitCardRepository.save(benefitCard);

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }



    }
