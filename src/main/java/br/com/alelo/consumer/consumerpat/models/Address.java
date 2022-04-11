package br.com.alelo.consumer.consumerpat.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String street;
    @Column
    private int number;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private int portalCode;
}
