package br.com.alelo.consumer.consumerpat.domain.exceptions;

public enum ErrorMessages {

    CARD_NOT_FOUND("O cartão número [%s] do consumidor [%s] não foi encontrado."),
    CARD_NOT_ESTABLISHMENT_TYPE("O cartão número [%s] não é do tipo de estabelecimento informado."),
    CONSUMER_NOT_FOUND("Não foi encontrado o consumidor com identificador [%s]."),
    CONSUMERS_NOT_FOUND("Não foram encontrados consumidores no banco de dados."),
    EXTRACTS_NOT_FOUND("Não foram encontrados extratos para o consumidor com identificador [%s].");

    ErrorMessages(String message){
        this.message = message;
    }

    private final String message;

    public String message() {
        return this.message;
    }
}
