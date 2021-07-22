//package br.com.alelo.consumer.consumerpat.service;
//
//import br.com.alelo.consumer.consumerpat.dto.PurchaseDTO;
//import br.com.alelo.consumer.consumerpat.entity.Card;
//import br.com.alelo.consumer.consumerpat.entity.Consumer;
//import br.com.alelo.consumer.consumerpat.entity.Extract;
//import br.com.alelo.consumer.consumerpat.entity.Type;
//import br.com.alelo.consumer.consumerpat.respository.CardRepository;
//import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
//import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//import static br.com.alelo.consumer.consumerpat.entity.Type.DRUGSTORE_CARD_NUMBER;
//import static br.com.alelo.consumer.consumerpat.entity.Type.FOOD_CARD_NUMBER;
//
//@Service
//public class PurchaseService {
//
//    @Autowired
//    private ConsumerRepository consumerRepository;
//
//    @Autowired
//    private ExtractRepository extractRepository;
//
//    @Autowired
//    private CardRepository cardRepository;
//
//    public void setBalance(Integer cardNUmber, double value) {
//
//        Consumer consumer = null;
//        Card card = null;
//        consumerRepository.findByDrugstoreNumber(cardNUmber);
//
//        if (consumer != null) {
//            // é cartão de farmácia
//            card.setDrugstoreCardBalance(card.getDrugstoreCardBalance() + value);
//            consumerRepository.save(consumer);
//        } else {
//            consumer = consumerRepository.findByFoodCardNumber(cardNUmber);
//            if (consumer != null) {
//                // é cartão de refeição
//                card.setFoodCardBalance(card.getFoodCardBalance() + value);
//                consumerRepository.save(consumer);
//            } else {
//                // É cartão de combustivel
//                consumer = consumerRepository.findByFuelCardNumber(cardNUmber);
//                card.setFuelCardBalance(card.getFuelCardBalance() + value);
//                consumerRepository.save(consumer);
//            }
//        }
//    }
//
//    public Extract buy(Integer cardNumber, PurchaseDTO purchaseDTO) {
//
//        Consumer consumer = null;
//        Card card = null;
//
//            if (purchaseDTO.getEstablishmentName() == FOOD_CARD_NUMBER.getName()) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (purchaseDTO.getValue() / 100) * 10;
//            purchaseDTO.setValue(purchaseDTO.getValue() - cashback);
//
//            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
//                card.setFoodCardBalance(card.getFoodCardBalance() - purchaseDTO.getValue());
//                consumerRepository.save(consumer);
//
//        }else if((purchaseDTO.getEstablishmentName() == DRUGSTORE_CARD_NUMBER.getName())) {
//            consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
//                card.setDrugstoreCardBalance(card.getDrugstoreCardBalance() - purchaseDTO.getValue());
//                consumerRepository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (purchaseDTO.getValue() / 100) * 35;
//            purchaseDTO.setValue(purchaseDTO.getValue() + tax);
//
//            consumer = consumerRepository.findByFuelCardNumber(cardNumber);
//                card.setFuelCardBalance(card.getFuelCardBalance() - purchaseDTO.getValue());
//                consumerRepository.save(consumer);
//        }
//
//        Extract extract = new Extract(purchaseDTO.getEstablishmentName(), purchaseDTO.getProductDescription(), new Date(), purchaseDTO.getCardNumber(), purchaseDTO.getValue());
//        return extractRepository.save(extract);
//    }
//
//}
