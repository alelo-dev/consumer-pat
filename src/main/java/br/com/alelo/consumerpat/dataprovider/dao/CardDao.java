package br.com.alelo.consumerpat.dataprovider.dao;

import br.com.alelo.consumerpat.dataprovider.entity.CardEntity;

public interface CardDao extends BaseDao<CardEntity> {

    CardEntity findByCardNumber(String cardNumber);
}
