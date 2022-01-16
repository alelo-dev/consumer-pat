package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@DiscriminatorValue("FUEL_CARD")
public class FuelCard extends Card {

    @Transient
    private BigDecimal taxedValue;

    public FuelCard(CardDTO c) {
        super.setCardType(c.getCardType());
        super.setNumber(c.getNumber());
        super.setBalance(c.getBalance() == null ? BigDecimal.ZERO : c.getBalance());
    }

    public FuelCard() {

    }

    /**
     * Adicional de 35 ao valor do produto
     * @param value do produto
     */
    private void calculateTax(final BigDecimal value) {

        BigDecimal tax = value.divide(new BigDecimal("100.0")).multiply(new BigDecimal("35.0"));

        taxedValue = value.add(tax);
    }

    @Override
    public void subtractBalance(BigDecimal val) {

        calculateTax(val);

        this.setBalance(getBalance().subtract(taxedValue));
    }
}