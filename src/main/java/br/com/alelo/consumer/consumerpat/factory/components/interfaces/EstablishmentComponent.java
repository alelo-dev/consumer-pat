package br.com.alelo.consumer.consumerpat.factory.components.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;

public interface EstablishmentComponent {
    void debit(BuyDTO buy);
}
