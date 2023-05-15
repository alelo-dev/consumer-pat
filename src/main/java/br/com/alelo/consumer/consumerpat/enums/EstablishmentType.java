package br.com.alelo.consumer.consumerpat.enums;


        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
        *
        * Tipos dos estabelcimentos:
        *    1) Alimentação (Food)
        *    2) Farmácia (DrugStore)
        *    3) Posto de combustivel (Fuel)
        */


private enum EstablishmentType {
    Alimentacao(1), 
    Farmacia(2), 
    Posto_combustivel(3);

    private final int code;

    private EstablishmentType(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    public String toString() {
        return String.valueOf(code);
    }
}