package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    String street;
    int number;
    String city;
    String country;
    int portalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
