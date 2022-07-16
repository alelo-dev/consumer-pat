package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Cliente que possui os dados controlados e transações gerenciadas.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumer {

    // OBSERVAÇÃO: Não apliquei Bean Validation pois não havia nenhuma validação explicitamente definida no teste.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // CONSUMER PERSONAL DATA
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    // CONSUMER CONTACTS
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    // CONSUMER ADDRESS
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    // CONSUMER CARDS
    private Integer foodCardNumber;

    @Column(updatable = false)
    private Double foodCardBalance;

    private Integer fuelCardNumber;

    @Column(updatable = false)
    private Double fuelCardBalance;

    private Integer drugstoreCardNumber;

    @Column(updatable = false)
    private Double drugstoreCardBalance;

}
