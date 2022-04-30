package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumer implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private long documentNumber;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

    //contacts
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    //Address
    private String street;
    private String number;
    private String city;
    private String country;
    private Integer portalCode;

    //cards
    private Long foodCardNumber;

    @Column(precision = 20, scale = 2)
    private BigDecimal foodCardBalance = BigDecimal.ZERO;

    private Long fuelCardNumber;

    @Column(precision = 20, scale = 2)
    private BigDecimal fuelCardBalance  = BigDecimal.ZERO;

    private Long drugstoreNumber;

    @Column(precision = 20, scale = 2)
    private BigDecimal drugstoreCardBalance = BigDecimal.ZERO;

    public Consumer(String name, long documentNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public Consumer(String name, long documentNumber, Long foodCardNumber, Long fuelCardNumber, Long drugstoreNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.foodCardNumber = foodCardNumber;
        this.fuelCardNumber = fuelCardNumber;
        this.drugstoreNumber = drugstoreNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                && number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Objects.equals(consumer.foodCardBalance, foodCardBalance)
                && fuelCardNumber == consumer.fuelCardNumber && Objects.equals(consumer.fuelCardBalance, fuelCardBalance)
                && drugstoreNumber == consumer.drugstoreNumber && Objects.equals(consumer.drugstoreCardBalance, drugstoreCardBalance)
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }

}
