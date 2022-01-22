package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    //contacts
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumer", nullable = false)
    private List<Phone> phoneList;
    @Column(name = "EMAIL")
    private String email;

    //Address
    @Column(name = "STREET")
    private String street;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "PORTAL_CODE")
    private Integer postalCode;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumer", nullable = false)
    private List<Card> cardList;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && number == consumer.number
                && postalCode == consumer.postalCode
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }

}
