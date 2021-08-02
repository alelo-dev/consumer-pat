package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ReloadCardDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;

    public void reloadCard(ReloadCardDTO reloadCardDTO) {
        Card card = cardRepository.findByCardNumber(reloadCardDTO.getCardNumber())
                .orElseThrow(ResourceNotFoundException::new);

        BigDecimal value = reloadCardDTO.getValue().add(card.getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        card.setBalance(value);

        cardRepository.save(card);
    }

}
