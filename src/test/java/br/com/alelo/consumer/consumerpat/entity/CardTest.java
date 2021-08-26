package br.com.alelo.consumer.consumerpat.entity;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;

public class CardTest {

    @Test
    public void adicionaSaldo() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.addBalance(100);
        Assert.isTrue(card.getBalance().compareTo(new BigDecimal(100)) == 0, "Deve conter 100 de saldo");
        Assert.isTrue(card.getBalance().doubleValue() == 100, "Deve conter 100 de saldo");
    }

    @Test
    public void criaItemExtratoCartao() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.addBalance(666);
        Assert.isTrue(card.getCardHistory().size() == 1, "Deve conter um item em historico do cartao");
        Assert.isTrue(card.getCardHistory().get(0).getValue().doubleValue() == 666, "Deve conter 666 no unico registro de historico");
    }
}
