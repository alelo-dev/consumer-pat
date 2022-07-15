package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.web.vo.card.UpdateCardFormVO;

public interface CardService {

    Card findByCardNumber(Long cardNumber);

    Card updateCardBalance(Long cardNumber, UpdateCardFormVO form);


}
