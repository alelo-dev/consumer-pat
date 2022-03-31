package br.com.alelo.consumer.consumerpat.service.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.BuyDTO;

public interface CardService {
    BalanceDTO setBalance(BalanceDTO balance);

    BalanceDTO buy(BuyDTO buy);
}
