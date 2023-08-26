package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CardCreditService {

    private final CardRepository cardRepository;

    public void setBalance(Integer cardNumber , Double value) throws ChangeSetPersister.NotFoundException {
        var card = cardRepository.findCardByCardNumber(cardNumber).orElseThrow(ChangeSetPersister.NotFoundException::new);
        card.setCardBalance(card.getCardBalance() + value);
        cardRepository.save(card);
    }

    public Card save(Card card) {
        Optional<Card> cardOp = cardRepository.findCardByCardNumber(card.getCardNumber());
        if(cardOp.isEmpty()) {
            return cardRepository.save(card);
        }
        return null;
    }
}
