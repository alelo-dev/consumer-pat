package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumerpat.core.usecase.CardRechargeUseCase;
import br.com.alelo.consumerpat.dataprovider.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardRechargeUseCaseImpl implements CardRechargeUseCase {

    @Autowired
    private CardRepository cardDao;

    @Override
    public void recharge(String cardNumber, CardRechargeV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvalidRechargeException {
        CardDomain cardDomain = this.cardDao.findByCardNumber(cardNumber);

        if (cardDomain == null) {
            throw new CardNotFoundException();
        }

        cardDomain.recharge(requestDto.getValue());
        cardDomain.validateBalance();

        this.cardDao.save(cardDomain);
    }
}
