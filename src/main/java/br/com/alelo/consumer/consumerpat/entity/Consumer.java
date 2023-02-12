package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    private String identifier;
    private String name;
    private long documentNumber;
    private LocalDate birthDate;

    //contacts
    private long mobilePhoneNumber;
    private long residencePhoneNumber;
    private long phoneNumber;
    private String email;

    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int postalCode;

    //cards
    private Long foodCardNumber;
    private BigDecimal foodCardBalance;

    private Long fuelCardNumber;
    private BigDecimal fuelCardBalance;

    private Long drugstoreNumber;
    private BigDecimal drugstoreCardBalance;

}