package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @Column(name = "consumer_id")
    private Long id;

    private String street;

    private String city;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String email;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
}
