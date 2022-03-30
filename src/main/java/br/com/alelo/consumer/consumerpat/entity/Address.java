package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STREET", nullable = false, length = LengthFieldsBD.LENGTH_200)
    private String street;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "CITY", nullable = false, length = LengthFieldsBD.LENGTH_100)
    private String city;

    @Column(name = "COUNTRY", nullable = false, length = LengthFieldsBD.LENGTH_100)
    private String country;

    @Column(name = "PORTAL_CODE", nullable = false)
    private Integer portalCode;

    @OneToOne
    @JoinColumn(name = "CONSUMER_ID", nullable = false)
    private Consumer consumer;
}
