package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

//@JsonTypeName("food_card")
@Entity
@DiscriminatorValue("FOOD_CARD")
public class FoodCard extends Card {

    @Transient
    private BigDecimal amountDiscounted;

    public FoodCard(CardDTO c) {
        super.setCardType(c.getCardType());
        super.setNumber(c.getNumber());
        super.setBalance(c.getBalance() == null ? BigDecimal.ZERO : c.getBalance());
    }

    public FoodCard() {

    }

    private void calculateCachback(final BigDecimal value) {
        BigDecimal cachback = value.divide(new BigDecimal("100.0")).multiply(new BigDecimal("10.0"));

        amountDiscounted = value.subtract(cachback);
    }

    @Override
    public void subtractBalance(final BigDecimal value) {

        calculateCachback(value);

        this.setBalance(getBalance().subtract(amountDiscounted));
    }

}
