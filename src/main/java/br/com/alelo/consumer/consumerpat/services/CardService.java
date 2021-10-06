package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;

public interface CardService {

    /**
     * Método responsável por creditar um valor ao cartão do usuário
     */
    Card setBalance(String cardNumber, Double value);

    /**
     * Método responsável por realizar o débito do cartão do
     * usuário de acordo com o tipo de compra realizada
     */
    Card buy(Purchase purchase);
}
