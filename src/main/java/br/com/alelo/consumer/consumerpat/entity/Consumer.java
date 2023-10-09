package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int documentNumber;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int portalCode;

    @Column(nullable = false)
    private int mobilePhoneNumber;

    @Column(nullable = true)
    private int residencePhoneNumber;

    @Column(nullable = true)
    private int phoneNumber;

    @Column(nullable = false)
    private String email;

}

