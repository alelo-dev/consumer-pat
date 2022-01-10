package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BuyFoodService implements BuyService {

    @Autowired
    private CardService cardService;

    @Override
    public void buy(Long cardNumber, BigDecimal value) throws Exception{
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        BigDecimal cashback  = value.divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.TEN);
        value = value.subtract(cashback);

        cardService.creditBalance(cardNumber, value);
    }
}
