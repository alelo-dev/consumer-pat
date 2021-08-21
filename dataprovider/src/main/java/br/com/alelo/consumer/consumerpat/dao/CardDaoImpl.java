package br.com.alelo.consumer.consumerpat.dao;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.CardDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
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
}
