package br.com.alelo.consumer.consumerpat.parser.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface CardBalanceParser {
    BalanceDTO parse(Card card);
}
