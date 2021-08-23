package br.com.alelo.consumer.consumerpat.core.usecase.impl;

import br.com.alelo.consumer.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumer.consumerpat.dataprovider.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import br.com.alelo.consumer.consumerpat.core.usecase.CardRechargeUseCase;
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
