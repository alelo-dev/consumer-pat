package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Card extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private CardTypeEnum cardType;

    @NotNull
    Integer number;

    @NotNull
    BigDecimal balance = BigDecimal.ZERO;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public void increaseBalance(BigDecimal value) {
        this.balance = balance.add(value);
    }

    public void decreaseBalance(BigDecimal value) {

        this.balance = balance.subtract(value);
        if (balance.doubleValue() < 0) {
            balance = BigDecimal.ZERO;
        }
    }
}
