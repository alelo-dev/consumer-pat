package br.com.alelo.consumer.consumerpat.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardTypeId;

    private String name;

    public boolean isFood() {
        return cardTypeId != null && CardTypeEnum.of(cardTypeId).equals(CardTypeEnum.FOOD);
    }

    public boolean isDrugstore() {
        return cardTypeId != null && CardTypeEnum.of(cardTypeId).equals(CardTypeEnum.DRUGSTORE);
    }

    public boolean isFuel() {
        return cardTypeId != null && CardTypeEnum.of(cardTypeId).equals(CardTypeEnum.FUEL);
    }
}
