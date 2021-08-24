package br.com.alelo.consumer.consumerpat.business;

import java.math.BigDecimal;
import java.util.Optional;
import br.com.alelo.consumer.consumerpat.dto.v2.CardDTO;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;

public interface ICardControllerBusiness {
    Optional<CardDTO> updateBalance (Integer cardNumber, BigDecimal value, CardTypeEnum type);
}
