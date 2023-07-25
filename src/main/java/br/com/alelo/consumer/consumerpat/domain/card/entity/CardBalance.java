package br.com.alelo.consumer.consumerpat.domain.card.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;

@Getter
@ToString
public class CardBalance {

    private UUID id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Card card;

    public CardBalance(UUID id, Card card) {
        this.id = id;
        this.amount = ZERO;
        this.card = card;
    }

    public void chargeCardBalance(final BigDecimal value) {
        this.amount = this.amount.add(value);
    }

    public void withdrawCardBalance(final BigDecimal value) {
        if (this.amount.compareTo(value) < 0) {
            throw new DomainException("Unavailable balance");
        }
        this.amount = this.amount.subtract(value);
    }
}
