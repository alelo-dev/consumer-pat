package br.com.alelo.consumer.consumerpat.factory.components.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface EstablishmentComponent {
    Card debit(BuyDTO buy);
}
