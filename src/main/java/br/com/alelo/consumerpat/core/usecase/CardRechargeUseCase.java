package br.com.alelo.consumerpat.core.usecase;

import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;

public interface CardRechargeUseCase {
    void recharge(String cardNumber, CardRechargeV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvalidRechargeException;
}
