package br.com.alelo.consumer.consumerpat.service;

public interface CardService {

    /**
     * Altera o saldo do cartão
     *
     * @param cardNumber - Número do cartão
     * @param value - Valor da operação
     */
    void setBalance(final String cardNumber, final Double value);

    /**
     * Cria uma operação de compra
     * Para compras no cartão de alimentação  o value recebe um desconto de 10%;
     * Para compras no cartão de combustivel o value recebe um acrescimo de 35%;
     *
     * @param establishmentType - Tipo do estabelecimento
     * @param establishmentName - Nome do estabelecimento
     * @param cardNumber - Número do cartão
     * @param productDescription - Descrição do produto
     * @param value - Valor da operação
     */
    void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription, Double value);
}
