package br.com.alelo.consumer.consumerpat.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", length = 256, nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @Column(name = "country", length = 60, nullable = false)
    private String country;

    @Column(name = "postal_code", nullable = false)
    private Long postalCode;

}
