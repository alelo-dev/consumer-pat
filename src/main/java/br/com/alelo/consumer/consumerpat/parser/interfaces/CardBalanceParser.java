package br.com.alelo.consumer.consumerpat.parser.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardBalanceParser {
    BalanceDTO parse(Card card);
}
