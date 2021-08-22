package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.dataprovider.dao.CardDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumerpat.core.mapper.domain.CardDomainMapper;
import br.com.alelo.consumerpat.core.usecase.CardRechargeUseCase;
import br.com.alelo.consumerpat.core.v1.request.CardRechargeV1RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardRechargeUseCaseImpl implements CardRechargeUseCase {

    @Autowired
    private CardDao cardDao;

    @Override
    public void recharge(String cardNumber, CardRechargeV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvalidRechargeException {
        CardEntity cardEntity = this.cardDao.findByCardNumber(cardNumber);

        if (cardEntity == null) {
            throw new CardNotFoundException();
        }

        CardDomain cardDomain = CardDomainMapper.convert(cardEntity);
        cardDomain.recharge(requestDto.getValue());
        cardDomain.validateBalance();

        cardEntity.setBalance(cardDomain.getBalance());

        this.cardDao.save(cardEntity);
    }
}
