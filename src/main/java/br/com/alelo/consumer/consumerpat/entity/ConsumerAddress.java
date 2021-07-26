package br.com.alelo.consumer.consumerpat.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 13:46
 */

import br.com.alelo.consumer.consumerpat.model.enums.CountryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String city;
    private CountryEnum country;
    private Integer portalCode;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public ConsumerAddress(String street, String number, String city, CountryEnum country, Integer portalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
        this.createdAt = LocalDateTime.now();
    }

    public ConsumerAddress(Integer id, String street, String number, String city, CountryEnum country, Integer portalCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
        this.createdAt = LocalDateTime.now();
    }
}
