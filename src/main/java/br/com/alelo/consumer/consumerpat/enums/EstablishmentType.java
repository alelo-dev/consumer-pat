package br.com.alelo.consumer.consumerpat.enums;

/* 
    Tipos de estabelcimentos
    1 - Alimentação (food)
    2 - Farmácia (DrugStore)
    3 - Posto de combustivel (Fuel)
*/
public enum EstablishmentType {
    FOOD(1), 
    DRUGSTORE(2), 
    FUEL(3);

    public final int value;

    private EstablishmentType(int value) {
        this.value = value;
    }    
}
