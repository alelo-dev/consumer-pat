package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BuyFuelService implements BuyService {

    @Autowired
    private CardService cardService;

    @Override
    public void buy(Long cardNumber, BigDecimal value) throws Exception {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        BigDecimal tax  = value.divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.valueOf(35L));
        value = value.add(tax);

        cardService.creditBalance(cardNumber, value);
    }
}
