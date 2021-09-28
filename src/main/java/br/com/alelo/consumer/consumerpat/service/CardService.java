package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;

public interface CardService {

    void setBalance(int cardNumber, double value) throws InsuficientBalanceException, CardNotFoundException;
    ExtractEntity buy(BuyDTO buyDTO) throws CardNotFoundException, EstablishmentInvalidException, InsuficientBalanceException;
}
