package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.web.vo.card.UpdateCardFormVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Override
    public Card findByCardNumber(Long cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Card not found!"));
    }

    @Override
    public Card updateCardBalance(Long cardNumber, UpdateCardFormVO form) {
        Card card = cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Card not found!"));

        card.setBalance(card.getBalance().add(form.getValue()));

        return cardRepository.save(card);
    }


}
