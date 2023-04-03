package br.com.alelo.consumer.consumerpat.service.impl;


import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.ApiException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.request.SettlingRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    public CardResponse updateBalance(String cardNumber, BigDecimal value) {
        var card = cardRepository.findByNumber(cardNumber);
        card.add(value);
        return cardMapper.toResponse(cardRepository.save(card));
    }

    public CardResponse updateSettlement(SettlingRequest settlingRequest) {
        Card card = cardRepository.findByNumber(settlingRequest.getCardNumber());
        if (!card.getCardType().equals(settlingRequest.getCardType()))
            throw new ApiException("The establishment does not accept this type of card.");

        card.subtract(settlingRequest.getValue());
        return cardMapper.toResponse(cardRepository.save(card));
    }
}
