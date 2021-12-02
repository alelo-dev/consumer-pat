package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Objects;

import static java.util.Objects.isNull;

@Data
@Entity(name = "CARDS")
public class CardsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FOOD_CARD_NUMBER")
    private int foodCardNumber;

    @Column(name = "FOOD_CARD_BALANCE")
    private double foodCardBalance;

    @Column(name = "FUEL_CARD_NUMBER")
    private int fuelCardNumber;

    @Column(name = "FUEL_CARD_BALANCE")
    private double fuelCardBalance;

    @Column(name = "DRUGSTORE_CARD_NUMBER")
    private int drugstoreCardNumber;

    @Column(name = "DRUGSTORE_CARD_BALANCE")
    private double drugstoreCardBalance;

    @Override
    public boolean equals(Object obj) {
        if (isNull(obj) || getClass() != obj.getClass()) return false;
        CardsInfo card = (CardsInfo) obj;
        return Objects.equals(id, card.id)
                && Double.compare(card.foodCardBalance, foodCardBalance) == 0
                && Double.compare(card.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Double.compare(card.fuelCardBalance, fuelCardBalance) == 0;

    }
}
