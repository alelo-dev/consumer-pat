package br.com.alelo.consumer.consumerpat.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alelo.consumer.consumerpat.domain.exception.InsufficientBalanceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "consumer_cards")
public class Card {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @Setter(value = AccessLevel.NONE)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;

    public void addCredit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void debitBalanceAmount(BigDecimal paymentAmount) {
        if (isBalanceInsufficient(paymentAmount)) {
            throw new InsufficientBalanceException("Your current balance is insufficient for this transaction");
        }

        this.balance = this.balance.subtract(paymentAmount);
    }

    private boolean isBalanceInsufficient(BigDecimal totalAmount) {
        return this.balance.compareTo(totalAmount) < 0;
    }
}
