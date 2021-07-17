package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

import java.math.BigDecimal;

/**
 * Responsible for securing card service subscriptions
 *
 * @author mcrj
 */
public interface ICardService {

    /**
     * Update card value by numbering
     *
     * @param cardNumber - Card number
     * @param value - new value
     * @return {@link Card} - Updated Card Value
     */
    Card updateBalance(final Long cardNumber, final BigDecimal value);

    /**
     * Buying process
     *
     * @param dto - Representation of values buy
     * @return - {@link Card} - Updated Card data
     */
    Card buy(final RequestBuyDTO dto);
}
