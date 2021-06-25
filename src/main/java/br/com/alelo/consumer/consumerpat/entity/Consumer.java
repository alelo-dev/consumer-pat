package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@EqualsAndHashCode
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    //contacts
    @Embedded
    private Contact contact;

    //Address
    @Embedded
    private Address address;

    //cards
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "foodCardNumber")),
        @AttributeOverride(name = "balance", column = @Column(name = "foodCardBalance"))
    })
    @Embedded
    private FoodCard foodCard;
    
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "fuelCardNumber")),
        @AttributeOverride(name = "balance", column = @Column(name = "fuelCardBalance"))
    })
    @Embedded
    private FuelCard fuelCard;
    
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "drugstoreCardNumber")),
        @AttributeOverride(name = "balance", column = @Column(name = "drugstoreCardBalance"))
    })
    @Embedded
    private DrugstoreCard drugstoreCard;
}
