package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

import java.math.BigDecimal;

public interface CardService {

    Card updateBalance(final Long cardNumber, final BigDecimal value);

    Card buy(final BuyDTO dto);
}
