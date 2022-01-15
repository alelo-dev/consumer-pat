package br.com.alelo.consumer.consumerpat.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("FUEL_CARD")
public class FuelCard extends Card {

    @Override
    public double cardBalance() {
        return 1;
    }
}
