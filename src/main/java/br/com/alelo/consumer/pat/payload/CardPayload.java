package br.com.alelo.consumer.pat.payload;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@Builder
public class CardPayload {

    private Long cardId;

    @Enumerated(EnumType.STRING)
    private EstablishmentType establishmentType;

    private String cardNumber;

    private BigDecimal cardBalance;

    public static CardPayload from(Card card) {
        return CardPayload.builder()
            .cardId(card.getId())
            .establishmentType(card.getEstablishmentType())
            .cardNumber(card.getCardNumber())
            .cardBalance(card.getBalance())
            .build();
    }

}
