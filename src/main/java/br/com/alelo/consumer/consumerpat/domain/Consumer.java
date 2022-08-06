package br.com.alelo.consumer.consumerpat.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "residence_phone_number")
    private String residencePhoneNumber;

    private String email;

    private String street;

    @Column(name = "number_residency")
    private Integer numberResidency;

    private String city;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToMany
    @JoinColumn(name = "consumer_id")
    private List<Card> cards = new ArrayList<>();
}
