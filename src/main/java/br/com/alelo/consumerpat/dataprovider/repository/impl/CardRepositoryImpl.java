package br.com.alelo.consumerpat.dataprovider.repository.impl;

import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.mapper.domain.CardDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.CardEntityMapper;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.CardEntity;
import br.com.alelo.consumerpat.dataprovider.jpa.repository.CardJpaRepository;
import br.com.alelo.consumerpat.dataprovider.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CardRepositoryImpl implements CardRepository {

    @Autowired
    private CardJpaRepository repository;

    @Override
    public CardDomain save(CardDomain domain) {
        CardEntity cardEntity = CardEntityMapper.convert(domain);

        return CardDomainMapper.convert(this.repository.save(cardEntity));
    }

    @Override
    public CardDomain findByCardNumber(String cardNumber) {
        return CardDomainMapper.convert(this.repository.findByCardNumber(cardNumber));
    }

    @Override
    public List<CardDomain> findByCardNumberIn(List<String> cardsNumber) {
        return this.repository.findByCardNumberIn(cardsNumber).stream().map(CardDomainMapper::convert)
                .collect(Collectors.toList());
    }
}
