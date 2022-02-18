package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.card.ICard;
import br.com.alelo.consumer.consumerpat.enums.ECard;
import br.com.alelo.consumer.consumerpat.respository.CardRepostiory;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.vo.CardVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class CardService {

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private CardRepostiory cardRepository;

    public Card setBalance(CardVo cardVo) {

        try {
            Card card = cardRepository.findById(cardVo.getNumber()).orElseThrow(() ->  new Exception("Cartão não encontrado"));
            card.setBalance(card.getBalance().add(cardVo.getValue()));
            cardRepository.saveAndFlush(card);
        } catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

//        Consumer consumer = null;
//        consumer = repository.findByDrugstoreNumber(cardNumber);
//
//        if(consumer != null) {
//            // é cartão de farmácia
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
//            repository.save(consumer);
//        } else {
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            if(consumer != null) {
//                // é cartão de refeição
//                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
//                repository.save(consumer);
//            } else {
//                // É cartão de combustivel
//                consumer = repository.findByFuelCardNumber(cardNumber);
//                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
//                repository.save(consumer);
//            }
//        }
        return null;
    }

    public Extract buy(CardVo cardVo) {

        try{

            ICard cardStr = (ICard) ECard.findById(cardVo.getEstablishmentId()).getCard().getDeclaredConstructors()[0].newInstance();
            Card card = cardRepository.findById(cardVo.getNumber()).orElseThrow(() -> new Exception("Cartão não encontrado"));
            card.setBalance(cardStr.calculateBalance());
            cardRepository.saveAndFlush(card);

        } catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

//        Consumer consumer = null;
//        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
//         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
//         *
//         * Tipos de estabelcimentos
//         * 1 - Alimentação (food)
//         * 2 - Farmácia (DrugStore)
//         * 3 - Posto de combustivel (Fuel)
//         */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            repository.save(consumer);
//
//        }else if(establishmentType == 2) {
//            consumer = repository.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            repository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = repository.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            repository.save(consumer);
//        }

//        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
//        extractRepository.save(extract);

        return null;
    }

}
