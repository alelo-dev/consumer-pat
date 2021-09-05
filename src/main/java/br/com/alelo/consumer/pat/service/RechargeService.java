package br.com.alelo.consumer.pat.service;

import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.exception.CardNotFoundException;
import br.com.alelo.consumer.pat.respository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RechargeService {

    private final CardRepository repository;

    public void rechargeCard(String cardNumber, BigDecimal value) {
        Card card = repository.findByCardNumber(cardNumber).orElseThrow(CardNotFoundException::new);

        card.setBalance(card.getBalance().add(value));
        card.setRechargedAt(LocalDateTime.now());

        repository.save(card);
    }

}
