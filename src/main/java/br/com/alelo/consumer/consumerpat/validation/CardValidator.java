//package br.com.alelo.consumer.consumerpat.validation;
//
//import br.com.alelo.consumer.consumerpat.entity.Card;
//import br.com.alelo.consumer.consumerpat.respository.CardRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//public class CardValidator {
//
//    private CardRepository repository;
//
//    public Card validateCard(Integer cardNumber, Double value) {
//
//        if (Objects.isNull(value)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<Card> findCard = repository.findByCardNumber(cardNumber);
//
//        if (findCard.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
//        }
//
//        return findCard.get();
//    }
//
//}
