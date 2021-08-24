package br.com.alelo.consumer.consumerpat.business.impl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.alelo.consumer.consumerpat.business.ICardControllerBusiness;
import br.com.alelo.consumer.consumerpat.dto.v2.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.ICardEntityRepository;

@Service
public class CardBusinessImpl implements ICardControllerBusiness{

    @Autowired
    ICardEntityRepository cardRepository;

    @Override
    public Optional<CardDTO> updateBalance(Integer cardNumber, BigDecimal value, CardTypeEnum type) {

        CardEntity cardToUpdate = cardRepository.findByNumberAndType(cardNumber, type.getValue());
        
        if (Objects.nonNull(cardToUpdate)){
            cardToUpdate.getCardBalance().add(value);
            cardRepository.save(cardToUpdate);
            return Optional.of(new CardDTO(cardToUpdate));
        }
        return Optional.empty();
    }
    
}
