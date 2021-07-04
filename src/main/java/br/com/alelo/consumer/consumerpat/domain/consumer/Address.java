package br.com.alelo.consumer.consumerpat.domain.consumer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "number")
    private int number;

    @Column(name = "country")
    private String country;

    @Column(name = "portal_code")
    private int portalCode;

    @OneToOne(mappedBy = "address")
    private Consumer consumer;

    @Builder
    public Address(String street, String city, int number, String country, int portalCode, Consumer consumer) {
        this.street = street;
        this.city = city;
        this.number = number;
        this.country = country;
        this.portalCode = portalCode;
        this.consumer = consumer;
    }
}
