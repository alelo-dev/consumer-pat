package br.com.alelo.consumer.consumerpat.factory.components.impls;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrugstoreComponentImpl implements EstablishmentComponent {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card debit(BuyDTO buy) {
        Card card = cardRepository.findById(buy.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));

        card.setBalance(card.getBalance() - buy.getValue());

        return cardRepository.save(card);
    }
}
