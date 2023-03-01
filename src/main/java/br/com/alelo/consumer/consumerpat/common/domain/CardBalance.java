package br.com.alelo.consumer.consumerpat.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Getter
@Setter
@AllArgsConstructor
public class CardBalance {

    private Card card;
    private BigDecimal amount;

    public boolean canWithdraw(BigDecimal withdrawAmount) {
        Predicate<BigDecimal> isPositiveOrZero = value -> value.compareTo(BigDecimal.ZERO) >= 0;
        var calculatedBalance = this.amount.subtract(withdrawAmount);

        return isPositiveOrZero.test(calculatedBalance);
    }
}
