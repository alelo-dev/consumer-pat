package br.com.alelo.consumer.consumerpat.service.validator;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.ICardRepository;
import br.com.alelo.consumer.consumerpat.util.BigDecimalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.alelo.consumer.consumerpat.enums.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.enums.CardType.FUEL;
import static java.util.Locale.getDefault;
import static org.springframework.http.HttpStatus.*;

/**
 * Validator for errors of process the card
 *
 * @author mcrj
 */
@Component
@RequiredArgsConstructor
public class CardValidator {

    private final ICardRepository repository;

    private final BigDecimalUtil bigDecimalUtil;

    private final MessageSource messageSource;

    /**
     * Validate card data to be manipulated
     *
     * @param cardNumber - Card number
     * @param value - Value card
     * @return {@link Card} - Card retrieved from base
     */
    public Card validCard(final Long cardNumber, final BigDecimal value) {
        if (Objects.isNull(value)) {
            throw new ResponseStatusException(BAD_REQUEST, messageSource.getMessage("msg.card.value.not.present",
                    null, getDefault()));
        }

        var findCard = repository.findByNumber(cardNumber);

        if (findCard.isEmpty()) {
            throw new ResponseStatusException(NO_CONTENT, messageSource.getMessage("msg.card.not.found",
                    null, getDefault()));
        }

        return findCard.get();
    }

    /**
     * Get new card value from type
     *
     * @param card - Card
     * @param cardType - Type card
     * @param value - new value for the balance
     * @return {@link BigDecimal} - New calculated value
     */
    public BigDecimal getAdditionForBalanceByType(final Card card, final CardType cardType,
                                                  final BigDecimal value) {

        if( !Objects.equals(card.getCardType(), cardType) ) {
            throw new ResponseStatusException(FORBIDDEN, messageSource.getMessage("msg.card.types.not.match",
                    null, getDefault()));
        }

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

        if ( balance.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new ResponseStatusException(FORBIDDEN, messageSource.getMessage("msg.card.insufficient.funds",
                    null, getDefault()));
        }

        return bigDecimalUtil.getValueWithScale(balance);
    }

    private boolean isFood(final Card card) {
        return card.getCardType().equals(FOOD);
    }

    private boolean isFuel(final Card card) {
        return card.getCardType().equals(FUEL);
    }

}
