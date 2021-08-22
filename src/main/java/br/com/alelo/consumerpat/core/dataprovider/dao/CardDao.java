package br.com.alelo.consumerpat.core.dataprovider.dao;

import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;

public interface CardDao extends BaseDao<CardEntity> {

    CardEntity findByCardNumber(String cardNumber);
}
