package br.com.alelo.consumer.consumerpat.util;

public enum EstablishmentType {

    ALIMENTACAO(1),
    FARMACIA(2),
    POSTO(3);

    Integer codigo;

    EstablishmentType(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo(){
        return this.codigo;
    }

}
