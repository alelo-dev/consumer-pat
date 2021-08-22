package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.dataprovider.dao.CardDao;
import br.com.alelo.consumerpat.core.dataprovider.dao.ExtractDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.dataprovider.entity.ExtractEntity;
import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.mapper.domain.CardDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.CardEntityMapper;
import br.com.alelo.consumerpat.core.mapper.entity.ExtractEntityMapper;
import br.com.alelo.consumerpat.core.usecase.BuyUseCase;
import br.com.alelo.consumerpat.core.v1.request.CardBuyV1RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

@Service
public class BuyUseCaseImpl implements BuyUseCase {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private ExtractDao extractDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateBalance(String cardNumber, CardBuyV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException {
        CardEntity cardEntity = this.cardDao.findByCardNumber(cardNumber);

        if (cardEntity == null) {
            throw new CardNotFoundException();
        }

        CardDomain cardDomain = CardDomainMapper.convert(cardEntity);
        cardDomain.calculateBalance(requestDto.getEstablishmentType(), requestDto.getValue());

        CardEntity newCardEntity = CardEntityMapper.convert(cardDomain);
        newCardEntity.setId(cardEntity.getId());
        newCardEntity.setCardNumber(cardNumber);
        newCardEntity.setConsumer(cardEntity.getConsumer());
        ExtractEntity extractEntity = ExtractEntityMapper.convert(requestDto, cardEntity, cardDomain);

        this.cardDao.save(newCardEntity);
        this.extractDao.save(extractEntity);
    }
}
