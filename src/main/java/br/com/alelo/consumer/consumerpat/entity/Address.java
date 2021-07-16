package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Address extends BaseEntity {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;
}
