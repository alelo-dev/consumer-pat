package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardRepository extends BaseRepository<Card> {

    Card findByCardNumber(Long cardNumber);
}
