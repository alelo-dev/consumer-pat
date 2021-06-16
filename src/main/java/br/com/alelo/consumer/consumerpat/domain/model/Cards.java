package br.com.alelo.consumer.consumerpat.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int foodCardNumber;
    private double foodCardBalance;

    private int fuelCardNumber;
    private double fuelCardBalance;

    private int drugstoreNumber;
    private double drugstoreCardBalance;
}
