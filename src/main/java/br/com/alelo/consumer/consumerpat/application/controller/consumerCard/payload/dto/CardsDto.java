package br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.dto;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {
    Card car;
    BigDecimal amount;

    public static CardsDto of(Card car, BigDecimal amount) {
        return new CardsDto(car, amount);
    }
}
