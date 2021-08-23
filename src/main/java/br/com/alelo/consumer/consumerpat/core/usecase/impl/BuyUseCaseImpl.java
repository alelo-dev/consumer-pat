package br.com.alelo.consumer.consumerpat.core.usecase.impl;

import br.com.alelo.consumer.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumer.consumerpat.dataprovider.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.dataprovider.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumer.consumerpat.core.domain.ExtractDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumer.consumerpat.core.mapper.domain.ExtractDomainMapper;
import br.com.alelo.consumer.consumerpat.core.usecase.BuyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

@Service
public class BuyUseCaseImpl implements BuyUseCase {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateBalance(String cardNumber, CardBuyV1RequestDto requestDto) throws CardNotFoundException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException {
        CardDomain cardDomain = this.cardRepository.findByCardNumber(cardNumber);

        if (cardDomain == null) {
            throw new CardNotFoundException();
        }

        cardDomain.calculateBalance(requestDto.getEstablishmentType(), requestDto.getValue());
        ExtractDomain extractDomain = ExtractDomainMapper.convert(requestDto, cardDomain);

        this.cardRepository.save(cardDomain);
        this.extractRepository.save(extractDomain);
    }
}
