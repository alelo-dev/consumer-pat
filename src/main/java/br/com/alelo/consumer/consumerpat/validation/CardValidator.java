package br.com.alelo.consumer.consumerpat.validation;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.alelo.consumer.consumerpat.enums.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.enums.CardType.FUEL;
import static org.springframework.http.HttpStatus.*;

import static java.math.RoundingMode.HALF_UP;

@Component
@RequiredArgsConstructor
public class CardValidator {

    private final CardRepository repository;

    public BigDecimal getNewBalance(final Card card, final CardType cardType,
                                    final BigDecimal value) {

        if( !Objects.equals(card.getCardType(), cardType) ) {
            throw new ResponseStatusException(FORBIDDEN, "Cartão não encontrado");
        }

        BigDecimal newBalance = getNewBalanceAccordingToTheType(card, value);

        if ( newBalance.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new ResponseStatusException(FORBIDDEN, "Saldo insuficiente");
        }

        return newBalance.setScale(2, HALF_UP);
    }

    public BigDecimal getNewBalanceAccordingToTheType(Card card, BigDecimal value) {
        BigDecimal cashback;
        BigDecimal addition;
        BigDecimal balance = card.getBalance();

        if (isFood(card)) {
            cashback = value.subtract(value.multiply(BigDecimal.valueOf(0.1)));
            balance = balance.subtract(cashback);
        } else if(isFuel(card)) {
            addition = value.add(value.multiply(BigDecimal.valueOf(0.35)));
            balance = balance.subtract(addition);
        } else {
            balance = balance.subtract(value);
        }

        return balance;
    }

    public Card validateCard(final Long cardNumber, final BigDecimal value) {
        if (Objects.isNull(value)) {
            throw new ResponseStatusException(BAD_REQUEST, "Número do cartão não informado");
        }

        var findCard = repository.findByNumber(cardNumber);

        if (findCard.isEmpty()) {
            throw new ResponseStatusException(NO_CONTENT, "Cartão não encontrado");
        }

        return findCard.get();
    }

    private boolean isFood(final Card card) {
        return card.getCardType().equals(FOOD);
    }

    private boolean isFuel(final Card card) {
        return card.getCardType().equals(FUEL);
    }

}
