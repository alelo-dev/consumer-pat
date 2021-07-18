package br.com.alelo.consumer.consumerpat.entity;

public enum Estabelecimentos {

    FOOD(1, "Food"),
    DRUGSTORE(2, "DrugStore"),
    FUEL(3, "Fuel");

    private int sigla;
    private String descricao;

    Estabelecimentos(int sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public int getSigla() {
        return sigla;
    }

    public void setSigla(int sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
