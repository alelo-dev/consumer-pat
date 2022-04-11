package br.com.alelo.consumer.consumerpat.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private int foodCardNumber;
    @Column
    private double foodCardBalance;
    @Column
    private int fuelCardNumber;
    @Column
    private double fuelCardBalance;
    @Column
    private int drugstoreNumber;
    @Column
    private double drugstoreCardBalance;
}
