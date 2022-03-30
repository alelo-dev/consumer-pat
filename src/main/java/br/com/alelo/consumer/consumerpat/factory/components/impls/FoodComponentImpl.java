package br.com.alelo.consumer.consumerpat.factory.components.impls;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import br.com.alelo.consumer.consumerpat.parser.interfaces.CardBalanceParser;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodComponentImpl implements EstablishmentComponent {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card debit(BuyDTO buy) {
        Double cashback = (buy.getValue() / 100) * 10;
        Double value = buy.getValue() - cashback;
        Card card = cardRepository.findById(buy.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));

        card.setBalance(card.getBalance() - value);

        return cardRepository.save(card);
    }
}
