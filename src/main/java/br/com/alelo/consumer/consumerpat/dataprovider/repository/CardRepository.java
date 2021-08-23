package br.com.alelo.consumer.consumerpat.dataprovider.repository;

import br.com.alelo.consumer.consumerpat.core.domain.CardDomain;

import java.util.List;

public interface CardRepository extends BaseRepository<CardDomain> {

    CardDomain findByCardNumber(String cardNumber);

    List<CardDomain> findByCardNumberIn(List<String> cardsNumber);
}
