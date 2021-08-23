package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;

public interface CardRechargeUseCase {
    void recharge(String cardNumber, CardRechargeV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvalidRechargeException;
}
