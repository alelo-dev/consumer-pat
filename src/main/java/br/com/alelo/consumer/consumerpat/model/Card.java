package br.com.alelo.consumer.consumerpat.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.alelo.consumer.consumerpat.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.model.type.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Consumer consumer;

    @Column(nullable = false, length = 19)
    private String number;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CardType type;

    public void bue(final BigDecimal value) {
        var newBalance = this.balance.subtract(value);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsuficientBalanceException();
        }

        this.balance = newBalance;
    }

    public BigDecimal caulculateValueWithTax(final BigDecimal amount) {
        return amount.multiply(type.getTax());
    }

    public Card mergeWithoutBalance(final Card card) {
        this.number = card.number;
        this.type   = card.type;
        return this;
    }

}
