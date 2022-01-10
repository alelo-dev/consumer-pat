package br.com.alelo.consumer.consumerpat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BuyDrugstoreService implements BuyService {

    @Autowired
    private CardService cardService;

    @Override
    public void buy(Long cardNumber, BigDecimal value) throws Exception{
        cardService.creditBalance(cardNumber, value);
    }
}
