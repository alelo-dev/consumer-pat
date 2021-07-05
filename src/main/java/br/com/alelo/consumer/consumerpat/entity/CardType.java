package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;

@Getter
public enum CardType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3),
    ;

    int codigo;

    CardType(int codigo){
        this.codigo = codigo;
    }
}
