package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "cardType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DrugstoreCard.class, name = "DRUGSTORE_CARD"),
        @JsonSubTypes.Type(value = FoodCard.class, name = "FOOD_CARD"),
        @JsonSubTypes.Type(value = FuelCard.class, name = "FUEL_CARD")})
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cardType", discriminatorType = DiscriminatorType.STRING)
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "cardType", insertable = false, updatable = false)
    private CardType cardType;

    public abstract double cardBalance();
}
