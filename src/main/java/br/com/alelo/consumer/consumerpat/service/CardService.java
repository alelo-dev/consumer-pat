package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.CardType;
import br.com.alelo.consumer.consumerpat.model.Transaction;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.function.Function;

import static java.util.Map.entry;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    private static final String CARD_NOT_FOUND_MSG = "Card Not Found";

    private static final BigDecimal FUEL_TAX = BigDecimal.valueOf(0.35);

    private static final BigDecimal FOOD_CASHBACK = BigDecimal.valueOf(0.1);

    private static final Map<CardType, Function<BigDecimal, BigDecimal>> CARD_RULES =
            Map.ofEntries(
                    entry(CardType.Food, CardService::calculateFoodCashback),
                    entry(CardType.Drugstore, CardService::calculateDrugstoreTax),
                    entry(CardType.Fuel, CardService::caculateFuelTax));

    public Card increaseFunds(BigInteger cardNumber, BigDecimal value) {
        throwExceptionIfValueToIncreaseFundsIsZeroOrNegative(value);
        Card card = cardRepository.findByNumber(cardNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND_MSG));
        card.setFunds(card.getFunds().add(value));
        cardRepository.save(card);
        return card;
    }

    private void throwExceptionIfValueToIncreaseFundsIsZeroOrNegative(BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) >= 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Zero or Negative Card funds not allowed");
        }
    }

    public Card charge(String establishmentName, BigInteger cardNumber, String productDescription, BigDecimal value) {

        Card card = cardRepository.findByNumber(cardNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND_MSG));

        BigDecimal updatedValue = CARD_RULES.get(card.getType()).apply(value);
        throwExceptionIfCardHasInsufficientFunds(card, updatedValue);
        card.setFunds(card.getFunds().subtract(updatedValue));
        cardRepository.save(card);

        Transaction transaction = new Transaction();
        transaction.setEstablishmentName(establishmentName);
        transaction.setProductDescription(productDescription);
        transaction.setPurchaseDateTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        transaction.setCardNumber(cardNumber);
        transaction.setValue(updatedValue);
        extractRepository.save(transaction);

        return card;
    }

    private static BigDecimal calculateDrugstoreTax(BigDecimal value) {
        return value;
    }

    private static BigDecimal caculateFuelTax(BigDecimal value) {
        BigDecimal tax  = value.multiply(FUEL_TAX);
        return value.add(tax);
    }

    private static BigDecimal calculateFoodCashback(BigDecimal value) {
        BigDecimal cashback = value.multiply(FOOD_CASHBACK);
        return value.subtract(cashback);
    }

    private void throwExceptionIfCardHasInsufficientFunds(Card card, BigDecimal value) {
        if (card.getFunds().compareTo(value) < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Insufficient Funds");
        }
    }
}
