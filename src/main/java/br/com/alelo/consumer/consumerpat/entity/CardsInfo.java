package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(name = "DRUGSTORE_NUMBER")
    private int drugstoreNumber;

    @Column(name = "DRUGSTORE_CARD_BALANCE")
    private double drugstoreCardBalance;

}
