package br.com.alelo.consumerpat.core.usecase;

import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;

import java.lang.reflect.InvocationTargetException;

public interface BuyUseCase {

    void calculateBalance(String cardNumber, CardBuyV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException;
}
