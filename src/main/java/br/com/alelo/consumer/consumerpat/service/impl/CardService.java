package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;

    public void setBalance(BalanceDTO dto) {
        var card = cardRepository.findCardByCardNumber(dto.getCardNumber()).orElseThrow();
        card.setCardBalance(card.getCardBalance() + dto.getValue());
        cardRepository.save(card);
    }

}
