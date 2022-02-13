package br.com.alelo.consumer.consumerpat.business;

/**
 * Interface para regras de cartão
 */
public interface Rule {

    /**
     * @param value Valor da compra
     * @return Valor novo de acordo com a regra implementada
     */
    Double applyBuyRule(Double value);
}
