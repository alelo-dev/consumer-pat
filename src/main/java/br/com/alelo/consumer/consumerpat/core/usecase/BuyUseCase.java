package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;

import java.lang.reflect.InvocationTargetException;

public interface BuyUseCase {

    void calculateBalance(String cardNumber, CardBuyV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException;
}
