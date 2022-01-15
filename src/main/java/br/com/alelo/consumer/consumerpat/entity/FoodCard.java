package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@JsonTypeName("food_card")
@Getter
@Setter
@Entity
@DiscriminatorValue("FOOD_CARD")
public class FoodCard extends Card {

    @Override
    public double cardBalance() {
        return 2;
    }
}
