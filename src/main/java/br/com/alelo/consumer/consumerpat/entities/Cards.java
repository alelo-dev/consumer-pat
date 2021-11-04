package br.com.alelo.consumer.consumerpat.entities;

import br.com.alelo.consumer.consumerpat.enumeraters.ESTABLISHMENT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Cards implements Serializable {

    private static final long serialVersionUID = 3957001277976318950L;
    @Transient
    private ESTABLISHMENT establishmentType;
    @Column(name = "FOOD_CARD_NUMBER")
    private Integer foodCardNumber;
    @Column(name = "FOOD_CARD_BALANCE", precision = 3)
    private BigDecimal foodCardBalance = BigDecimal.ZERO;
    @Column(name = "FUEL_CARD_NUMBER")
    private Integer fuelCardNumber;
    @Column(name = "FUEL_CARD_BALANCE", precision = 3)
    private BigDecimal fuelCardBalance = BigDecimal.ZERO;
    @Column(name = "DRUGSTORE_NUMBER")
    private Integer drugstoreCardNumber;
    @Column(name = "DRUGSTORE_BALANCE", precision = 3)
    private BigDecimal drugstoreCardBalance = BigDecimal.ZERO;

    public Cards() {
        initializeEstablishmentType();
    }

    @Transient
    private void initializeEstablishmentType() {
        if (foodCardNumber != null) {
            establishmentType = ESTABLISHMENT.FOOD;
        } else if (fuelCardNumber != null) {
            establishmentType = ESTABLISHMENT.FUEL;
        } else if (drugstoreCardNumber != null) {
            establishmentType = ESTABLISHMENT.DRUGSTORE;
        }
    }

    @Transient
    /**
     * Para compras no cartão de alimentação o cliente recebe um desconto de 10%
     * Nas compras com o cartão de combustivel existe um acrescimo de 35%
     *
     * @param value
     */
    public void setBuyBalance(BigDecimal value) {
        if (establishmentType == ESTABLISHMENT.FOOD) {
            BigDecimal cashback = value.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.TEN);
            foodCardBalance = foodCardBalance.subtract(value.subtract(cashback));
        } else if (establishmentType == ESTABLISHMENT.DRUGSTORE) {
            drugstoreCardBalance = drugstoreCardBalance.subtract(value);
        } else if (establishmentType == ESTABLISHMENT.FUEL) {
            BigDecimal tax = value.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(35));
            fuelCardBalance = fuelCardBalance.subtract(value.add(tax));
        }
    }

    @Transient
    public void setBalance(BigDecimal value) {
        if (foodCardNumber != null) {
            foodCardBalance = foodCardBalance.add(value);
        } else if (fuelCardNumber != null) {
            fuelCardBalance = fuelCardBalance.add(value);
        } else if (drugstoreCardNumber != null) {
            drugstoreCardBalance = drugstoreCardBalance.add(value);
        }
    }
}
