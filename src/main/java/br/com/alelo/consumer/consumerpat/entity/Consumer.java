package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer documentNumber;
    @Column
    private LocalDate birthDate;

    // Contact
    @Column
    private Integer mobilePhoneNumber;
    @Column
    private Integer residencePhoneNumber;
    @Column
    private Integer phoneNumber;
    @Column
    private String email;

    //Address
    @Column
    private String street;
    @Column
    private Integer number;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private Integer portalCode;

    //cards
    @Column
    private Integer foodCardNumber;
    @Column
    private Double foodCardBalance;
    @Column
    private Integer fuelCardNumber;
    @Column
    private Double fuelCardBalance;
    @Column
    private Integer drugstoreCardNumber;
    @Column
    private Double drugstoreCardBalance;

}
