package br.com.alelo.consumer.consumerpat.data.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "STREET", nullable = false, length = 100)
    String street;

    @Column(name = "NUMBER", nullable = false)
    Integer number;

    @Column(name = "CITY", nullable = false, length = 50)
    String city;

    @Column(name = "COUNTRY", nullable = false, length = 50)
    String country;

    @Column(name = "POSTAL_CODE", nullable = false, length = 9)
    String postalCode;

    @Column(name = "ID_CONSUMER")
    Integer consumerId;
}
