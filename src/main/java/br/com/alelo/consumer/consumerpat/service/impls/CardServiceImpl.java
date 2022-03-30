package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void setBalance(BalanceDTO balance) {
        Card card = cardRepository.findById(balance.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));

        card.setBalance(card.getBalance() + balance.getValue());
        cardRepository.save(card);
    }
}
