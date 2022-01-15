package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@JsonTypeName("drugstore_card")
@Getter
@Setter
@Entity
@DiscriminatorValue("DRUGSTORE_CARD")
public class DrugstoreCard extends Card {

    @Override
    public double cardBalance() {
        return 3;
    }
}
