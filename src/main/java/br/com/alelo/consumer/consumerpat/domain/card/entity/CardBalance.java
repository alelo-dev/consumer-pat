package br.com.alelo.consumer.consumerpat.domain.card.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@ToString
public class CardBalance {

    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    @Valid
    @NotNull(message = "cardNumber is required")
    private CardNumber cardNumber;

    public CardBalance(CardNumber cardNumber) {
        this.amount = ZERO;
        this.cardNumber = cardNumber;
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
