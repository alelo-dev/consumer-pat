package br.com.alelo.consumer.consumerpat.service;

public enum EstablishmentType {
    ALIMENTACAO(1),
    FARMACIA(2),
    POST_COMBUSTIVEL(3);

    private int value;
    
    private  EstablishmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
