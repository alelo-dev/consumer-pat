package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Address {

    //Address
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")

    Integer id;
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    @JsonBackReference
    private Consumer consumer;

//    Integer consumerId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return
                this.number == address.number
                        && this.portalCode == address.portalCode
                        && Objects.equals(this.street, address.street)
                        && Objects.equals(this.city, address.city)
                        && Objects.equals(this.country, address.country)
                        && Objects.equals(this.id, address.id);
    }
}
