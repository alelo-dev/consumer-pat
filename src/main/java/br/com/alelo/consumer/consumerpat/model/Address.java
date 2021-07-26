package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Consumer consumer;

    @Column(nullable = false, length = 300)
    private String street;

    private Integer number;

    @Column(nullable = false, length = 300)
    private String city;

    @Column(nullable = false, length = 300)
    String country;

    @Column(nullable = false, length = 20)
    String postalCode;

    public void merge(final Address address) {
        this.city       = address.city;
        this.country    = address.country;
        this.number     = address.number;
        this.postalCode = address.postalCode;
        this.street     = address.street;
    }

}
