package br.com.alelo.consumer.consumerpat.factory.components.impls;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuelComponentImpl implements EstablishmentComponent {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card debit(BuyDTO buy) {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        Double tax = (buy.getValue() / 100) * 35;
        Double value = buy.getValue() + tax;
        Card card = cardRepository.findById(buy.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));

        card.setBalance(card.getBalance() - value);

        return cardRepository.save(card);
    }
}
