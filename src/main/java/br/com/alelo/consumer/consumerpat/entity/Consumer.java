package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    @Column(unique = true)
    String documentNumber;
    Date birthDate;

    // contacts
    String mobilePhoneNumber;
    String residencePhoneNumber;
    String phoneNumber;
    String email;

    // Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    @JsonBackReference
    @OneToMany(mappedBy = "consumer")
    List<Card> cards;
}
