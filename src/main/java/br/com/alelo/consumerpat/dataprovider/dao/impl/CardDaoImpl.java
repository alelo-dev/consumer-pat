package br.com.alelo.consumerpat.dataprovider.dao.impl;

import br.com.alelo.consumerpat.dataprovider.dao.CardDao;
import br.com.alelo.consumerpat.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.dataprovider.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardDaoImpl implements CardDao {

    @Autowired
    private CardRepository repository;

    @Override
    public CardEntity save(CardEntity entity) {
        return this.repository.save(entity);
    }

    @Override
    public CardEntity findByCardNumber(String cardNumber) {
        return this.repository.findByCardNumber(cardNumber);
    }
}
