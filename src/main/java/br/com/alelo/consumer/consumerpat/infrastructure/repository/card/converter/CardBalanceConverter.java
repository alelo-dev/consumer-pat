package br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardBalanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardBalanceConverter {

    public CardBalanceEntity toEntity(final CardBalance cardBalance) {
        return CardBalanceEntity.builder()
                .cardNumber(cardBalance.getCardNumber().getCardNumber())
                .amount(cardBalance.getAmount())
                .build();
    }

    public CardBalance toDomain(final CardBalanceEntity cardBalanceEntity) {
        var cardBalance = new CardBalance(new CardNumber(cardBalanceEntity.getCardNumber()));
        cardBalance.chargeCardBalance(cardBalanceEntity.getAmount());
        return cardBalance;
    }
}
