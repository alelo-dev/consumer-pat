package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;

public interface CardService {

    public ResultStatus addCard(Long idConsumer, CardDTO cardDTO);
    public Optional<CardDTO> setBalance(String cardNumber, BigDecimal value);
    public Optional<BigDecimal> registerBuy(String cardNumber, BuyDTO buyDTO);
   
}
