package br.com.alelo.consumer.consumerpat.objectvalue;

public enum EstablishmentType {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);
    private final int value;
    private EstablishmentType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
